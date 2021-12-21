package com.example.fsm.context;

import com.example.fsm.FsmOrder;
import com.example.fsm.OrderInfo;
import com.example.fsm.event.OrderStateEvent;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
public class CreateOrderContext extends StateContext<CreateOrderContext> {

    private OrderInfo orderInfo;

    public CreateOrderContext(OrderStateEvent orderStateEvent, FsmOrder fsmOrder) {
        super(orderStateEvent, fsmOrder);
        setContext(this);
        setOrderInfo((OrderInfo) fsmOrder);
    }

    public void setEstimatePriceInfo(String price) {

    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }
}
