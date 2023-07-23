package com.example.fsm.business.context;

import com.example.app.common.entity.bo.OrderBo;
import com.example.fsm.FsmOrder;
import com.example.fsm.business.event.CreateEvent;
import com.example.fsm.context.StateContext;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
public class CreateOrderContext extends StateContext<CreateOrderContext> {

	private OrderBo orderInfo;

	public CreateOrderContext(CreateEvent event, FsmOrder fsmOrder) {
		super(event, fsmOrder);
		setOrderInfo((OrderBo) fsmOrder);
	}

	public void setEstimatePriceInfo(String price) {

	}

	public OrderBo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderBo orderInfo) {
		this.orderInfo = orderInfo;
	}
}
