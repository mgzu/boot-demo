package com.example.fsm;

import lombok.Builder;
import org.jetbrains.annotations.NotNull;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
@Builder
public class OrderInfo implements FsmOrder {
    @NotNull
    private String orderState;
    private String userId;
    private int serviceType;

    @NotNull
    private String orderId;
    @NotNull
    private String bizCode;
    @NotNull
    private String sceneId;

    @Override
    @NotNull
    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(@NotNull String orderState) {
        this.orderState = orderState;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getServiceType() {
        return serviceType;
    }

    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }

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
    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(@NotNull String bizCode) {
        this.bizCode = bizCode;
    }

    @Override
    @NotNull
    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(@NotNull String sceneId) {
        this.sceneId = sceneId;
    }
}
