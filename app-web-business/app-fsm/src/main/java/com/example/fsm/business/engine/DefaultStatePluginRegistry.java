package com.example.fsm.business.engine;

import com.example.framework.common.util.CastUtil;
import com.example.fsm.annotation.ProcessorPlugin;
import com.example.fsm.engine.StatePluginRegistry;
import com.example.fsm.plugin.PluginHandler;
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
	private static final Map<String, Map<String, Map<String, List<PluginHandler<?, ?>>>>> statePluginMap = new ConcurrentHashMap<>();

	@SuppressWarnings("DuplicatedCode")
	@Override
	public Object postProcessAfterInitialization(@NotNull Object bean, @NotNull String beanName) throws BeansException {
		if (bean instanceof PluginHandler<?, ?> pluginHandler
			&& bean.getClass().isAnnotationPresent(ProcessorPlugin.class)) {
			ProcessorPlugin annotation = bean.getClass().getAnnotation(ProcessorPlugin.class);
			String[] states = annotation.state();
			String event = annotation.event();
			String[] bizCodes = annotation.bizCode().length == 0 ? new String[]{"#"} : annotation.bizCode();
			String[] sceneIds = annotation.sceneId().length == 0 ? new String[]{"#"} : annotation.sceneId();
			initProcessMap(states, event, bizCodes, sceneIds, pluginHandler);
		}
		return bean;
	}

	private <T, C, E extends PluginHandler<T, C>> void initProcessMap(String[] states, String event, String[] bizCodes,
																	  String[] sceneIds, E processor) {
		for (String bizCode : bizCodes) {
			for (String sceneId : sceneIds) {
				Arrays.asList(states)
					.parallelStream()
					.forEach(orderStateEnum -> registerStateHandlers(orderStateEnum, event, bizCode, sceneId, processor));
			}
		}
	}

	/**
	 * 初始化状态机处理器
	 */
	public <T, C, E extends PluginHandler<T, C>> void registerStateHandlers(String orderStateEnum, String event, String bizCode,
																			String sceneId, E processor) {
		// state维度
		statePluginMap.computeIfAbsent(orderStateEnum, key -> new ConcurrentHashMap<>());
		Map<String, Map<String, List<PluginHandler<?, ?>>>> stateTransformEventEnumMap = statePluginMap.get(orderStateEnum);
		// event维度
		stateTransformEventEnumMap.computeIfAbsent(event, key -> new ConcurrentHashMap<>());
		// getBizCode and getSceneId
		Map<String, List<PluginHandler<?, ?>>> processorMap = stateTransformEventEnumMap.get(event);
		String bizCodeAndSceneId = bizCode + "@" + sceneId;
		processorMap.computeIfAbsent(bizCodeAndSceneId, key -> new CopyOnWriteArrayList<>());
		processorMap.get(bizCodeAndSceneId).add(processor);
	}

	@NotNull
	@Override
	public <T, C> List<PluginHandler<T, C>> acquireStatePlugin(String orderState, String eventType, String bizCode, String sceneId) {
		Map<String, Map<String, List<PluginHandler<?, ?>>>> stateTransformEventEnumMap = statePluginMap.get(orderState);
		if (stateTransformEventEnumMap == null) {
			return Collections.emptyList();
		}
		Map<String, List<PluginHandler<?, ?>>> pluginHandlerMap = stateTransformEventEnumMap.get(eventType);
		if (pluginHandlerMap == null) {
			return Collections.emptyList();
		}
		List<PluginHandler<?, ?>> pluginHandlerList = pluginHandlerMap.getOrDefault(bizCode + "@" + sceneId, Collections.emptyList());
		return CastUtil.safeFakeCast(pluginHandlerList);
	}
}
