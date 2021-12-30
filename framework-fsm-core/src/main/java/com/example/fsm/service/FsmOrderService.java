package com.example.fsm.service;

import com.example.fsm.FsmOrder;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
public interface FsmOrderService {
    FsmOrder getFsmOrder(String orderId);
}
