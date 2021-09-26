package com.example.demo.fsm.context;

import com.example.demo.fsm.FsmOrder;
import com.example.demo.fsm.OrderInfo;
import com.example.demo.fsm.event.OrderStateEvent;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
public class CreateOrderContext extends StateContext<CreateOrderContext> {

    public CreateOrderContext(OrderStateEvent orderStateEvent, FsmOrder fsmOrder) {
        super(orderStateEvent, fsmOrder);
    }

    public void setEstimatePriceInfo(String price) {

    }

    public OrderInfo getOrderInfo() {
        return null;
    }
}
