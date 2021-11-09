package com.example.fsm.event;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
public class CreateEvent implements OrderStateEvent {
    @Override
    public String getEventType() {
        return null;
    }

    @Override
    public String getOrderId() {
        return null;
    }

    @Override
    public boolean newCreate() {
        return false;
    }
}
