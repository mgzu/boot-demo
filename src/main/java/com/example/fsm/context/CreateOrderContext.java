package com.example.fsm.context;

import com.example.demo.business.entity.bo.OrderBo;
import com.example.fsm.FsmOrder;
import com.example.fsm.event.OrderStateEvent;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
public class CreateOrderContext extends StateContext<CreateOrderContext> {

    private OrderBo orderInfo;

    public CreateOrderContext(OrderStateEvent orderStateEvent, FsmOrder fsmOrder) {
        super(orderStateEvent, fsmOrder);
        setContext(this);
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
