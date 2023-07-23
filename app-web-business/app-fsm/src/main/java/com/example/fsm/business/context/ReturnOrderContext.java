package com.example.fsm.business.context;

import com.example.fsm.FsmOrder;
import com.example.fsm.business.event.ReturnEvent;
import com.example.fsm.context.StateContext;

/**
 * @author MaGuangZu
 * @since 2022-03-08
 */
public class ReturnOrderContext extends StateContext<ReturnOrderContext> {

	public ReturnOrderContext(ReturnEvent event, FsmOrder fsmOrder) {
		super(event, fsmOrder);
	}

}
