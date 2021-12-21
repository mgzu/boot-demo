package com.example.fsm.event;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 订单状态迁移事件
 */
public interface OrderStateEvent {
    /**
     * 订单状态事件
     *
     * @see {@link com.example.fsm.enums.OrderEventEnum} for more details
     */
    @NotNull
    String getEventType();

    /**
     * 订单ID
     */
    @NotNull
    String getOrderId();

    /**
     * 如果orderState不为空，则代表只有订单是当前状态才进行迁移
     */
    @Nullable
    default String orderState() {
        return null;
    }

    /**
     * 是否要新创建订单
     */
    boolean newCreate();
}
