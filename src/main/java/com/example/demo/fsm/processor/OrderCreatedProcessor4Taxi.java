package com.example.demo.fsm.processor;


import com.example.demo.fsm.annotation.OrderProcessor;
import com.example.demo.fsm.enums.OrderEventEnum;
import com.example.demo.fsm.enums.OrderStateEnum;

@OrderProcessor(state = OrderStateEnum.INIT, event = OrderEventEnum.CREATE, bizCode = "TAXI")
public class OrderCreatedProcessor4Taxi extends OrderCreatedProcessor {
    @Override
    protected String doPromotion() {
        return "taxt1";
    }
}
