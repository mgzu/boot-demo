package com.example.fsm.business.engine;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import com.example.fsm.FsmOrder;
import com.example.fsm.ServiceResult;
import com.example.fsm.annotation.FsmEngine;
import com.example.fsm.business.enums.ErrorCodeEnum;
import com.example.fsm.context.StateContext;
import com.example.fsm.engine.OrderFsmEngine;
import com.example.fsm.engine.StateProcessRegistry;
import com.example.fsm.event.OrderStateEvent;
import com.example.fsm.exception.FsmException;
import com.example.fsm.processor.AbstractStateProcessor;
import com.example.fsm.processor.StateProcessor;
import com.example.fsm.service.FsmOrderService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Constructor;
import java.util.*;

@Slf4j
@FsmEngine(scanBasePackage = "com.example")
@RequiredArgsConstructor
@Component
public class DefaultOrderFsmEngine implements OrderFsmEngine {

    private final FsmOrderService fsmOrderService;

    private final StateProcessRegistry stateProcessorRegistry;

    private final Map<String, Class<? extends StateContext<?>>> contextMap = new HashMap<>();

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        Class<? extends DefaultOrderFsmEngine> klass = this.getClass();
        String packageName;
        FsmEngine annotation = klass.getAnnotation(FsmEngine.class);
        if (annotation == null) {
            packageName = klass.getPackageName();
        } else {
            packageName = annotation.scanBasePackage();
        }
        Set<Class<?>> classSet = ClassUtil.scanPackage(packageName, clazz -> {
            Class<?> superclass = clazz.getSuperclass();
            if (superclass == null) {
                return false;
            }
            return clazz.getSuperclass().equals(StateContext.class);
        });
        classSet.forEach(clazz -> {
            log.debug("class name: {}", clazz.getName());
            Constructor<?>[] constructors = ReflectUtil.getConstructors(clazz);
            Arrays.stream(constructors).forEach(constructor -> {
                if (constructor.getParameterCount() == 2) {
                    contextMap.put(constructor.getParameterTypes()[0].getName(), (Class<? extends StateContext<?>>) constructor.getDeclaringClass());
                    log.debug("declaring class name: {}", constructor.getDeclaringClass());
                }
            });
        });
    }

    @NotNull
    @Override
    public <T> ServiceResult<T> sendEvent(@NotNull OrderStateEvent orderStateEvent) throws FsmException {
        FsmOrder fsmOrder = null;
        // ???????????????????????????????????????????????????????????????
        if (!orderStateEvent.newCreate()) {
            fsmOrder = this.fsmOrderService.getFsmOrder(orderStateEvent.getOrderId());
            if (fsmOrder == null) {
                throw new FsmException(ErrorCodeEnum.ORDER_NOT_FOUND);
            }
        }
        return sendEvent(orderStateEvent, fsmOrder);
    }

    @NotNull
    @Override
    public <T> ServiceResult<T> sendEvent(@NotNull OrderStateEvent orderStateEvent, FsmOrder fsmOrder) throws FsmException {
        Objects.requireNonNull(fsmOrder);
        // ???????????????????????????
        StateContext<?> context = this.getStateContext(orderStateEvent, fsmOrder);
        // ???????????????????????????
        StateProcessor stateProcessor = this.getStateProcessor(context);
        // ??????????????????
        return stateProcessor.action(context);
    }

    @NotNull
    private <T, C> StateProcessor<T, C> getStateProcessor(StateContext<C> context) {
        OrderStateEvent stateEvent = context.getOrderStateEvent();
        FsmOrder fsmOrder = context.getFsmOrder();
        // ????????????+???????????????????????????????????????????????????
        List<AbstractStateProcessor> processorList = stateProcessorRegistry.acquireStateProcess(fsmOrder.getOrderState(), stateEvent.getEventType(), fsmOrder.getBizCode(), fsmOrder.getSceneId());
        if (processorList.isEmpty()) {
            // ????????????????????????
            if (stateEvent.orderState() != null && !Objects.equals(stateEvent.orderState(), fsmOrder.getOrderState())) {
                throw new FsmException(ErrorCodeEnum.ORDER_STATE_NOT_MATCH);
            }
            throw new FsmException(ErrorCodeEnum.NOT_FOUND_PROCESSOR);
        }
        List<AbstractStateProcessor> processorResult = new ArrayList<>(processorList.size());
        // ?????????????????????????????????????????????
        for (AbstractStateProcessor processor : processorList) {
            if (processor.filter(context)) {
                processorResult.add(processor);
            }
        }
        if (CollectionUtils.isEmpty(processorResult)) {
            throw new FsmException(ErrorCodeEnum.NOT_FOUND_PROCESSOR);
        }
        if (processorResult.size() > 1) {
            throw new FsmException(ErrorCodeEnum.FOUND_MORE_PROCESSOR);
        }
        return processorResult.get(0);
    }

    @SneakyThrows
    @NotNull
    private StateContext<?> getStateContext(OrderStateEvent orderStateEvent, FsmOrder fsmOrder) {
        Class<? extends StateContext<?>> klass = contextMap.get(orderStateEvent.getClass().getName());
        return klass.getDeclaredConstructor(orderStateEvent.getClass(), FsmOrder.class).newInstance(orderStateEvent, fsmOrder);
    }
}
