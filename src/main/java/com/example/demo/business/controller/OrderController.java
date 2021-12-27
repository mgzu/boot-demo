package com.example.demo.business.controller;

import cn.hutool.core.util.IdUtil;
import com.example.demo.business.entity.bo.OrderBo;
import com.example.fsm.FsmOrder;
import com.example.fsm.engine.OrderFsmEngine;
import com.example.fsm.enums.BizCodeEnum;
import com.example.fsm.enums.OrderEventEnum;
import com.example.fsm.enums.OrderStateEnum;
import com.example.fsm.enums.SceneIdEnum;
import com.example.fsm.event.CreateEvent;
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
public class OrderController {

    private final OrderFsmEngine orderFsmEngine;

    @GetMapping
    public void get() throws Exception {
        FsmOrder order = OrderBo.builder()
                .orderId(IdUtil.simpleUUID())
                .orderState(OrderStateEnum.INIT)
                .bizCode(BizCodeEnum.FBA)
                .sceneId(SceneIdEnum.H5)
                .build();
        OrderStateEvent event = CreateEvent.builder()
                .eventType(OrderEventEnum.CREATE)
                .orderId(order.getOrderId())
                .build();
        orderFsmEngine.sendEvent(event, order);
        String id = IdUtil.simpleUUID();
        log.info(id);
    }
}