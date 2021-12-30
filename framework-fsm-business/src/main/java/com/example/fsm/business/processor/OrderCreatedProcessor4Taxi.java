package com.example.fsm.business.processor;


import com.example.fsm.business.enums.OrderEventEnum;
import com.example.fsm.business.enums.OrderStateEnum;
import com.example.fsm.annotation.OrderProcessor;

@OrderProcessor(state = OrderStateEnum.INIT, event = OrderEventEnum.CREATE, bizCode = "TAXI")
public class OrderCreatedProcessor4Taxi extends OrderCreatedProcessor {
    @Override
    protected String doPromotion() {
        return "taxt1";
    }
}
