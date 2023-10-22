package com.example.fsm.business.engine;

import com.example.fsm.FsmOrder;
import com.example.fsm.context.StateContext;
import com.example.fsm.event.OrderStateEvent;

public interface IStateContextFactory {

	<C> StateContext<C> create(OrderStateEvent orderStateEvent, FsmOrder fsmOrder);

}
