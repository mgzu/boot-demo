package com.example.fsm.business;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import com.example.app.common.entity.bo.OrderBo;
import com.example.fsm.FsmOrder;
import com.example.fsm.business.event.CreateEvent;
import com.example.fsm.context.StateContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author MaGuangZu
 * @since 2022-03-08
 */
@Slf4j
public class ContextTest {

    @SuppressWarnings("unchecked")
    @Test
    public void test1() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Set<Class<?>> classSet = ClassUtil.scanPackage("com.example.fsm", clazz -> {
            Class<?> superclass = clazz.getSuperclass();
            if (superclass == null) {
                return false;
            }
            return clazz.getSuperclass().equals(StateContext.class);
        });
        Map<String, Class<? extends StateContext<?>>> contextMap = new HashMap<>();
        classSet.forEach(clazz -> {
            log.info("class name: {}", clazz.getName());
            Constructor<?>[] constructors = ReflectUtil.getConstructors(clazz);
            Arrays.stream(constructors).forEach(constructor -> {
                if (constructor.getParameterCount() == 2) {
                    contextMap.put(constructor.getParameterTypes()[0].getName(), (Class<? extends StateContext<?>>) constructor.getDeclaringClass());
                    log.info("declaring class name: {}", constructor.getDeclaringClass());
                }
            });
        });
        Class<? extends StateContext<?>> klass = contextMap.get("com.example.fsm.business.event.CreateEvent");
        CreateEvent build = CreateEvent.builder().eventType("1").build();
        OrderBo order = new OrderBo();
        StateContext<?> stateContext = klass.getDeclaredConstructor(build.getClass(), FsmOrder.class).newInstance(build, order);
        log.info("class name: {}", stateContext.getClass().getName());
    }
}
