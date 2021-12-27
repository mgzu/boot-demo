package com.example.demo.business.service.impl;

import com.example.demo.business.repository.OrderRepository;
import com.example.fsm.FsmOrder;
import com.example.fsm.service.FsmOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
@RequiredArgsConstructor
@Component
public class FsmOrderServiceImpl implements FsmOrderService {

    private final OrderRepository orderInfoRepository;

    @Override
    public FsmOrder getFsmOrder(String orderId) {
        return orderInfoRepository.getById(orderId);
    }
}
