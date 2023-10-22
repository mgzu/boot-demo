package com.example.app.order.event;

import com.example.fsm.event.OrderStateEvent;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;

/**
 * @author MaGuangZu
 * @since 2022-03-09
 */
@Builder
public class ReturnEvent implements OrderStateEvent {
    private String orderId;
    @NotNull
    private String eventType;

    @Override
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
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
