package com.example.app.order.controller;

import com.example.app.common.entity.bo.OrderBo;
import com.example.framework.web.entity.Result;
import com.example.framework.web.controller.BaseController;
import com.example.fsm.FsmOrder;
import com.example.fsm.business.enums.BizCodeEnum;
import com.example.fsm.business.enums.OrderEventEnum;
import com.example.fsm.business.enums.OrderStateEnum;
import com.example.fsm.business.enums.SceneIdEnum;
import com.example.fsm.business.event.CreateEvent;
import com.example.fsm.business.event.ReturnEvent;
import com.example.fsm.engine.OrderFsmEngine;
import com.example.fsm.event.OrderStateEvent;
import com.example.fsm.service.FsmOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.data.id.IdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MaGuangZu
 * @since 2021-12-22
 */
@RequiredArgsConstructor
@Slf4j
@RequestMapping("order")
@RestController
public class OrderController extends BaseController {

	private final OrderFsmEngine orderFsmEngine;
	private final FsmOrderService orderService;

	@GetMapping
	public Result<FsmOrder> get() {
		FsmOrder order = OrderBo.builder()
			.orderState(OrderStateEnum.INIT)
			.bizCode(BizCodeEnum.FBA)
			.sceneId(SceneIdEnum.H5)
			.orderId(IdUtil.getSnowflake().nextIdStr())
			.build();
		log.info("order id:" + order.getOrderId());
		OrderStateEvent event = CreateEvent.builder()
			.eventType(OrderEventEnum.CREATE)
			.orderId(order.getOrderId())
			.build();
		orderFsmEngine.sendEvent(event, order);
		@SuppressWarnings("ConstantConditions") // order id is not null
		FsmOrder fsmOrder = orderService.getFsmOrder(order.getOrderId());
		return Result.ok(fsmOrder);
	}

	@GetMapping("/return")
	public void returnOrder() {
		FsmOrder order = OrderBo.builder()
			.orderState(OrderStateEnum.INIT)
			.bizCode(BizCodeEnum.FBA)
			.sceneId(SceneIdEnum.H5)
			.build();
		OrderStateEvent event = ReturnEvent.builder()
			.eventType(OrderEventEnum.RETURN)
			.orderId(order.getOrderId())
			.build();
		orderFsmEngine.sendEvent(event, order);
	}
}
