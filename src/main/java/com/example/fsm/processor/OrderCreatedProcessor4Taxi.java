package com.example.fsm.processor;


import com.example.fsm.annotation.OrderProcessor;
import com.example.fsm.enums.OrderEventEnum;
import com.example.fsm.enums.OrderStateEnum;

@OrderProcessor(state = OrderStateEnum.INIT, event = OrderEventEnum.CREATE, bizCode = "TAXI")
public class OrderCreatedProcessor4Taxi extends OrderCreatedProcessor {
    @Override
    protected String doPromotion() {
        return "taxt1";
    }
}
