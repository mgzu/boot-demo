package com.example.fsm;

import org.jetbrains.annotations.NotNull;

/**
 * 状态机引擎所需的订单信息基类信息
 */
public interface FsmOrder {
    /**
     * 订单ID
     */
    @NotNull
    String getOrderId();

    /**
     * 订单状态
     */
    @NotNull
    String getOrderState();

    /**
     * 订单的业务属性
     */
    @NotNull
    String getBizCode();

    /**
     * 订单的场景属性
     */
    @NotNull
    String getSceneId();
}
