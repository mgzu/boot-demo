package com.example.fsm;

import lombok.Getter;
import lombok.Setter;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
@Setter
@Getter
public class OrderInfo implements FsmOrder {
    private String orderState;
    private String userId;
    private int serviceType;

    private String orderId;
    private String bizCode;
    private String sceneId;

}
