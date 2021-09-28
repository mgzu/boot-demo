package com.example.demo.fsm;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
public class OrderInfo implements FsmOrder {
    private String orderState;
    private String userId;
    private int serviceType;

    @Override
    public String getOrderId() {
        return null;
    }

    @Override
    public String getOrderState() {
        return null;
    }

    @Override
    public String bizCode() {
        return null;
    }

    @Override
    public String sceneId() {
        return null;
    }

    public void setOrderState(String nextState) {
        this.orderState = nextState;
    }

    public Object getUserId() {
        return this.userId;
    }

    public int getServiceType() {
        return this.serviceType;
    }
}
