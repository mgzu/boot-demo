package com.example.app.order.context;

import com.example.app.order.event.CreateEvent;
import com.example.app.order.event.ReturnEvent;
import com.example.fsm.FsmOrder;
import com.example.fsm.business.engine.IStateContextFactory;
import com.example.fsm.context.StateContext;
import com.example.fsm.event.OrderStateEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

@Component
public class StateContextFactory implements IStateContextFactory {

	static final Map<String, BiFunction<OrderStateEvent, FsmOrder, StateContext<?>>> contextMap = new HashMap<>();

	static {
		contextMap.put(CreateEvent.class.getName(), (orderStateEvent, fsmOrder) -> new CreateOrderContext((CreateEvent) orderStateEvent, fsmOrder));
		contextMap.put(ReturnEvent.class.getName(), (orderStateEvent, fsmOrder) -> new ReturnOrderContext((ReturnEvent) orderStateEvent, fsmOrder));
	}

	@NotNull
	public <C> StateContext<C> create(@NotNull OrderStateEvent orderStateEvent, @NotNull FsmOrder fsmOrder) {
		String clazzName = orderStateEvent.getClass().getName();
		BiFunction<OrderStateEvent, FsmOrder, StateContext<?>> function = contextMap.get(clazzName);
		if (function == null) {
			throw new IllegalArgumentException("unsupported event class:" + clazzName);
		}
		@SuppressWarnings("unchecked")
		StateContext<C> context = (StateContext<C>) function.apply(orderStateEvent, fsmOrder);
		return context;
	}

}
