package com.example.fsm.business.context;

import com.example.fsm.FsmOrder;
import com.example.fsm.business.event.CreateEvent;
import com.example.fsm.business.event.ReturnEvent;
import com.example.fsm.context.StateContext;
import com.example.fsm.event.OrderStateEvent;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

@UtilityClass
public class StateContextFactory {

	static final Map<String, BiFunction<OrderStateEvent, FsmOrder, StateContext<?>>> contextMap = new HashMap<>();

	static {
		contextMap.put(CreateEvent.class.getName(), (orderStateEvent, fsmOrder) -> new CreateOrderContext((CreateEvent) orderStateEvent, fsmOrder));
		contextMap.put(ReturnEvent.class.getName(), (orderStateEvent, fsmOrder) -> new ReturnOrderContext((ReturnEvent) orderStateEvent, fsmOrder));
	}

	@NotNull
	public static <C> StateContext<C> create(@NotNull OrderStateEvent orderStateEvent, @NotNull FsmOrder fsmOrder) {
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
