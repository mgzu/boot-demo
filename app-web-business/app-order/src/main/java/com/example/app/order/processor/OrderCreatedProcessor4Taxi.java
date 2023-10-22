package com.example.app.order.processor;


import com.example.fsm.annotation.OrderProcessor;
import com.example.app.order.checker.CreateParamChecker;
import com.example.app.order.enums.OrderEventEnum;
import com.example.app.order.enums.OrderStateEnum;
import com.example.fsm.business.repository.OrderRepository;
import com.example.fsm.checker.CheckerExecutor;
import com.example.fsm.plugin.PluginExecutor;

@OrderProcessor(
	state = OrderStateEnum.INIT,
	event = OrderEventEnum.CREATE,
	bizCode = "TAXI"
)
public class OrderCreatedProcessor4Taxi extends OrderCreatedProcessor {

	public OrderCreatedProcessor4Taxi(CheckerExecutor checkerExecutor, PluginExecutor pluginExecutor,
									  CreateParamChecker createParamChecker, OrderRepository orderRepository) {
		super(checkerExecutor, pluginExecutor, createParamChecker, orderRepository);
	}

	@Override
	protected String doPromotion() {
		return "taxt1";
	}
}
