package com.example.app.order.controller;

import com.example.app.common.entity.bo.OrderBo;
import com.example.framework.web.controller.BaseController;
import com.example.fsm.FsmOrder;
import com.example.fsm.business.enums.BizCodeEnum;
import com.example.fsm.business.enums.OrderEventEnum;
import com.example.fsm.business.enums.OrderStateEnum;
import com.example.fsm.business.enums.SceneIdEnum;
import com.example.fsm.business.event.CreateEvent;
import com.example.fsm.engine.OrderFsmEngine;
import com.example.fsm.event.OrderStateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping
    public void get() throws Exception {
        FsmOrder order = OrderBo.builder()
                .orderState(OrderStateEnum.INIT)
                .bizCode(BizCodeEnum.FBA)
                .sceneId(SceneIdEnum.H5)
                .build();
        OrderStateEvent event = CreateEvent.builder()
                .eventType(OrderEventEnum.CREATE)
                .orderId(order.getOrderId())
                .build();
        orderFsmEngine.sendEvent(event, order);
    }
}
