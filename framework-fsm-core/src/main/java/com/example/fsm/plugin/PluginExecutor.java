package com.example.fsm.plugin;

import com.example.fsm.FsmOrder;
import com.example.fsm.ServiceResult;
import com.example.fsm.context.StateContext;
import com.example.fsm.engine.StatePluginRegistry;
import com.example.fsm.exception.FsmException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class PluginExecutor {

	private final ExecutorService executor = Executors.newSingleThreadExecutor();

	@Autowired
	private StatePluginRegistry statePluginRegistry;

	public <T, C> void parallelExecutor(StateContext<C> context) {
		FsmOrder fsmOrder = context.getFsmOrder();
		List<PluginHandler<T, C>> handlerList = statePluginRegistry.acquireStatePlugin(fsmOrder.getOrderState(),
			context.getOrderStateEvent().getEventType(), fsmOrder.getBizCode(), fsmOrder.getSceneId());
		if (!handlerList.isEmpty()) {
			List<Future<ServiceResult<T>>> resultList = Collections.synchronizedList(new ArrayList<>());
			for (PluginHandler<T, C> handler : handlerList) {
				Future<ServiceResult<T>> result = executor.submit(() -> handler.action(context));
				resultList.add(result);
			}
			for (Future<ServiceResult<T>> future : resultList) {
				ServiceResult<T> sr;
				try {
					sr = future.get();
				} catch (Exception e) {
					log.error("parallelCheck executor.submit error.", e);
					Thread.currentThread().interrupt();
					throw new FsmException(e.getCause());
				}
				if (!sr.isSuccess()) {
					throw new FsmException(sr.getMessage());
				}
			}
		}
	}
}
