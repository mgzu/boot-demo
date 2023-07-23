package com.example.fsm.business.engine;

import com.example.fsm.FsmOrder;
import com.example.fsm.ServiceResult;
import com.example.fsm.business.context.StateContextFactory;
import com.example.fsm.business.enums.ErrorCodeEnum;
import com.example.fsm.context.StateContext;
import com.example.fsm.engine.OrderFsmEngine;
import com.example.fsm.engine.StateProcessRegistry;
import com.example.fsm.event.OrderStateEvent;
import com.example.fsm.exception.FsmException;
import com.example.fsm.processor.AbstractStateProcessor;
import com.example.fsm.processor.StateProcessor;
import com.example.fsm.service.FsmOrderService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class DefaultOrderFsmEngine implements OrderFsmEngine {

	private final FsmOrderService fsmOrderService;

	private final StateProcessRegistry stateProcessorRegistry;

	@NotNull
	@Override
	public <T> ServiceResult<T> sendEvent(@NotNull OrderStateEvent orderStateEvent) throws FsmException {
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
	public <T, C> ServiceResult<T> sendEvent(@NotNull OrderStateEvent orderStateEvent, FsmOrder fsmOrder) throws FsmException {
		Objects.requireNonNull(fsmOrder);
		// 构造当前事件上下文
		StateContext<C> context = this.getStateContext(orderStateEvent, fsmOrder);
		// 获取当前事件处理器
		StateProcessor<T, C> stateProcessor = this.getStateProcessor(context);
		// 执行处理逻辑
		return stateProcessor.action(context);
	}

	@NotNull
	private <T, C> StateProcessor<T, C> getStateProcessor(StateContext<C> context) {
		OrderStateEvent stateEvent = context.getOrderStateEvent();
		FsmOrder fsmOrder = context.getFsmOrder();
		// 根据状态+事件对象获取所对应的业务处理器集合
		List<AbstractStateProcessor<T, C>> processorList = stateProcessorRegistry.acquireStateProcess(fsmOrder.getOrderState(), stateEvent.getEventType(), fsmOrder.getBizCode(), fsmOrder.getSceneId());
		if (processorList.isEmpty()) {
			// 订单状态发生改变
			if (stateEvent.orderState() != null && !Objects.equals(stateEvent.orderState(), fsmOrder.getOrderState())) {
				throw new FsmException(ErrorCodeEnum.ORDER_STATE_NOT_MATCH);
			}
			throw new FsmException(ErrorCodeEnum.NOT_FOUND_PROCESSOR);
		}
		List<AbstractStateProcessor<T, C>> processorResult = new ArrayList<>(processorList.size());
		// 根据上下文获取唯一的业务处理器
		for (AbstractStateProcessor<T, C> processor : processorList) {
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
	private <C> StateContext<C> getStateContext(OrderStateEvent orderStateEvent, FsmOrder fsmOrder) {
		return StateContextFactory.create(orderStateEvent, fsmOrder);
	}
}
