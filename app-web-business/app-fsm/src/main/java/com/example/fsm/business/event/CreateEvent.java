package com.example.fsm.business.event;

import com.example.fsm.event.OrderStateEvent;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
@Builder
public class CreateEvent implements OrderStateEvent {

    @NotNull
    private String orderId;
    @NotNull
    private String eventType;

    @Override
    @NotNull
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(@NotNull String orderId) {
        this.orderId = orderId;
    }

    @Override
    @NotNull
    public String getEventType() {
        return eventType;
    }

    public void setEventType(@NotNull String eventType) {
        this.eventType = eventType;
    }

    @Override
    public boolean newCreate() {
        return true;
    }
}
