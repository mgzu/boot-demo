package com.example.fsm.business.engine;

import com.example.framework.common.util.CastUtil;
import com.example.fsm.annotation.OrderProcessor;
import com.example.fsm.engine.StateProcessRegistry;
import com.example.fsm.processor.AbstractStateProcessor;
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
public class DefaultStateProcessRegistry implements BeanPostProcessor, StateProcessRegistry {
	/**
	 * 第一层key是订单状态。
	 * 第二层key是订单状态对应的事件，一个状态可以有多个事件。
	 * 第三层key是具体场景code，场景下对应的多个处理器，需要后续进行过滤选择出一个具体的执行。
	 */
	private static final Map<String, Map<String, Map<String, List<AbstractStateProcessor<?, ?>>>>> stateProcessMap = new ConcurrentHashMap<>();

	@SuppressWarnings("DuplicatedCode")
	@Override
	public Object postProcessAfterInitialization(@NotNull Object bean, @NotNull String beanName) throws BeansException {
		if (bean instanceof AbstractStateProcessor<?, ?> abstractstateprocessor
			&& bean.getClass().isAnnotationPresent(OrderProcessor.class)) {
			OrderProcessor annotation = bean.getClass().getAnnotation(OrderProcessor.class);
			String[] states = annotation.state();
			String event = annotation.event();
			String[] bizCodes = annotation.bizCode().length == 0 ? new String[]{"#"} : annotation.bizCode();
			String[] sceneIds = annotation.sceneId().length == 0 ? new String[]{"#"} : annotation.sceneId();
			initProcessMap(states, event, bizCodes, sceneIds, abstractstateprocessor);
		}
		return bean;
	}

	private <T, C, E extends AbstractStateProcessor<T, C>> void initProcessMap(
		String[] states, String event, String[] bizCodes, String[] sceneIds, E processor) {
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
	public <T, C, E extends AbstractStateProcessor<T, C>> void registerStateHandlers(String orderStateEnum, String event, String bizCode,
																					 String sceneId, E processor) {
		// state维度
		stateProcessMap.computeIfAbsent(orderStateEnum, key -> new ConcurrentHashMap<>());
		Map<String, Map<String, List<AbstractStateProcessor<?, ?>>>> stateTransformEventEnumMap = stateProcessMap.get(orderStateEnum);
		// event维度
		stateTransformEventEnumMap.computeIfAbsent(event, key -> new ConcurrentHashMap<>());
		// getBizCode and getSceneId
		Map<String, List<AbstractStateProcessor<?, ?>>> processorMap = stateTransformEventEnumMap.get(event);
		String bizCodeAndSceneId = bizCode + "@" + sceneId;
		processorMap.computeIfAbsent(bizCodeAndSceneId, key -> new CopyOnWriteArrayList<>());
		processorMap.get(bizCodeAndSceneId).add(processor);
	}

	@NotNull
	@Override
	public <T, C> List<AbstractStateProcessor<T, C>> acquireStateProcess(String orderState, String eventType,
																		 String bizCode, String sceneId) {
		Map<String, Map<String, List<AbstractStateProcessor<?, ?>>>> stateTransformEventEnumMap = stateProcessMap.get(orderState);
		if (stateTransformEventEnumMap == null) {
			return Collections.emptyList();
		}
		Map<String, List<AbstractStateProcessor<?, ?>>> processorMap = stateTransformEventEnumMap.get(eventType);
		if (processorMap == null) {
			return Collections.emptyList();
		}
		List<AbstractStateProcessor<?, ?>> processorList = processorMap.getOrDefault(bizCode + "@" + sceneId, Collections.emptyList());
		return CastUtil.safeFakeCast(processorList);
	}
}
