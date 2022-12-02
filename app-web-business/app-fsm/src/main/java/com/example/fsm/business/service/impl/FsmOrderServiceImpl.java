package com.example.fsm.business.service.impl;

import com.example.app.common.entity.Order;
import com.example.fsm.FsmOrder;
import com.example.fsm.business.repository.OrderRepository;
import com.example.fsm.service.FsmOrderService;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
@RequiredArgsConstructor
@Component
public class FsmOrderServiceImpl implements FsmOrderService {

    private final OrderRepository orderRepository;

    @Override
    public FsmOrder getFsmOrder(@NotNull String orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        Optional<Order> one = orderRepository.findOne(Example.of(order));
        return one.orElseThrow(() -> new ServiceException("not found order:" + orderId));
    }
}
