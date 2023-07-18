package com.example.fsm.service;

import com.example.fsm.FsmOrder;
import org.jetbrains.annotations.NotNull;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
public interface FsmOrderService {

    FsmOrder getFsmOrder(@NotNull String orderId);

}
