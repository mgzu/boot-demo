package com.example.fsm.engine;


import com.example.fsm.FsmOrder;
import com.example.fsm.ServiceResult;
import com.example.fsm.context.CreateOrderContext;
import com.example.fsm.context.StateContext;
import com.example.fsm.enums.ErrorCodeEnum;
import com.example.fsm.enums.OrderEventEnum;
import com.example.fsm.event.OrderStateEvent;
import com.example.fsm.exception.FsmException;
import com.example.fsm.processor.AbstractStateProcessor;
import com.example.fsm.processor.StateProcessor;
import com.example.fsm.service.FsmOrderService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class DefaultOrderFsmEngine implements OrderFsmEngine {
    @Autowired
    private FsmOrderService fsmOrderService;
    @Autowired
    private StateProcessRegistry stateProcessorRegistry;

    @NotNull
    @Override
    public <T> ServiceResult<T> sendEvent(@NotNull OrderStateEvent orderStateEvent) throws Exception {
        FsmOrder fsmOrder = null;
        // 非新创建的订单，需要从数据库中查询订单信息
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
    public <T> ServiceResult<T> sendEvent(@NotNull OrderStateEvent orderStateEvent,
                                          @NotNull FsmOrder fsmOrder) throws Exception {
        Objects.requireNonNull(fsmOrder);
        // 构造当前事件上下文
        StateContext<?> context = this.getStateContext(orderStateEvent, fsmOrder);
        // 获取当前事件处理器
        StateProcessor stateProcessor = this.getStateProcessor(context);
        // 执行处理逻辑
        return stateProcessor.action(context);
    }

    @NotNull
    private <T> StateProcessor<T, ?> getStateProcessor(StateContext<?> context) {
        OrderStateEvent stateEvent = context.getOrderStateEvent();
        FsmOrder fsmOrder = context.getFsmOrder();
        // 根据状态+事件对象获取所对应的业务处理器集合
        List<AbstractStateProcessor> processorList = stateProcessorRegistry.acquireStateProcess(fsmOrder.getOrderState(),
                stateEvent.getEventType(), fsmOrder.getBizCode(), fsmOrder.getSceneId());
        if (processorList == null) {
            // 订单状态发生改变
            if (!Objects.isNull(stateEvent.orderState()) && !stateEvent.orderState().equals(fsmOrder.getOrderState())) {
                throw new FsmException(ErrorCodeEnum.ORDER_STATE_NOT_MATCH);
            }
            throw new FsmException(ErrorCodeEnum.NOT_FOUND_PROCESSOR);
        }
        List<AbstractStateProcessor> processorResult = new ArrayList<>(processorList.size());
        // 根据上下文获取唯一的业务处理器
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

    @NotNull
    private StateContext<?> getStateContext(OrderStateEvent orderStateEvent, FsmOrder fsmOrder) {
        StateContext<?> context = switch (orderStateEvent.getEventType()) {
            case OrderEventEnum.CREATE -> new CreateOrderContext(orderStateEvent, fsmOrder);
            default -> throw new FsmException(ErrorCodeEnum.UN_SUPPORT_EVENT);
        };
        return context;
    }
}
