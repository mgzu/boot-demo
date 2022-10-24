package com.example.fsm.business.engine;

import com.example.fsm.annotation.ProcessorPlugin;
import com.example.fsm.engine.StatePluginRegistry;
import com.example.fsm.plugin.PluginHandler;
import com.example.fsm.processor.StateProcessor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class DefaultStatePluginRegistry implements BeanPostProcessor, StatePluginRegistry {
    /**
     * 第一层key是订单状态。
     * 第二层key是订单状态对应的事件，一个状态可以有多个事件。
     * 第三层key是具体场景code，场景下对应的多个处理器，需要后续进行过滤选择出一个具体的执行。
     */
    private static Map<String, Map<String, Map<String, List<PluginHandler>>>> statePluginMap = new ConcurrentHashMap<>();

    @Override
    public Object postProcessAfterInitialization(@NotNull Object bean, @NotNull String beanName) throws BeansException {
        if (bean instanceof PluginHandler && bean.getClass().isAnnotationPresent(ProcessorPlugin.class)) {
            ProcessorPlugin annotation = bean.getClass().getAnnotation(ProcessorPlugin.class);
            String[] states = annotation.state();
            String event = annotation.event();
            String[] bizCodes = annotation.bizCode().length == 0 ? new String[]{"#"} : annotation.bizCode();
            String[] sceneIds = annotation.sceneId().length == 0 ? new String[]{"#"} : annotation.sceneId();
            initProcessMap(states, event, bizCodes, sceneIds, statePluginMap, (PluginHandler) bean);
        }
        return bean;
    }

    private <E extends StateProcessor> void initProcessMap(String[] states, String event, String[] bizCodes, String[] sceneIds,
                                                           Map<String, Map<String, Map<String, List<E>>>> map, E processor) {
        for (String bizCode : bizCodes) {
            for (String sceneId : sceneIds) {
                Arrays.asList(states).parallelStream().forEach(orderStateEnum -> {
                    registerStateHandlers(orderStateEnum, event, bizCode, sceneId, map, processor);
                });
            }
        }
    }

    /**
     * 初始化状态机处理器
     */
    public <E extends StateProcessor> void registerStateHandlers(String orderStateEnum, String event, String bizCode, String sceneId,
                                                                 Map<String, Map<String, Map<String, List<E>>>> map, E processor) {
        // state维度
        if (!map.containsKey(orderStateEnum)) {
            map.put(orderStateEnum, new ConcurrentHashMap<>());
        }
        Map<String, Map<String, List<E>>> stateTransformEventEnumMap = map.get(orderStateEnum);
        // event维度
        if (!stateTransformEventEnumMap.containsKey(event)) {
            stateTransformEventEnumMap.put(event, new ConcurrentHashMap<>());
        }
        // getBizCode and getSceneId
        Map<String, List<E>> processorMap = stateTransformEventEnumMap.get(event);
        String bizCodeAndSceneId = bizCode + "@" + sceneId;
        if (!processorMap.containsKey(bizCodeAndSceneId)) {
            processorMap.put(bizCodeAndSceneId, new CopyOnWriteArrayList<>());
        }
        processorMap.get(bizCodeAndSceneId).add(processor);
    }

    @NotNull
    @Override
    public List<PluginHandler> acquireStatePlugin(String orderState, String eventType, String bizCode, String sceneId) {
        Map<String, Map<String, List<PluginHandler>>> stateTransformEventEnumMap = statePluginMap.get(orderState);
        if (stateTransformEventEnumMap == null) {
            return Collections.emptyList();
        }
        Map<String, List<PluginHandler>> processorMap = stateTransformEventEnumMap.get(eventType);
        if (processorMap == null) {
            return Collections.emptyList();
        }
        return processorMap.getOrDefault(bizCode + "@" + sceneId, Collections.emptyList());
    }
}
