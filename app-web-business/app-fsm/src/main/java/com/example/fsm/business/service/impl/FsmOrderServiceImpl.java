package com.example.fsm.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.app.common.entity.Order;
import com.example.framework.common.exceptions.ServiceException;
import com.example.fsm.FsmOrder;
import com.example.fsm.business.mapper.OrderMapper;
import com.example.fsm.service.FsmOrderService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
@RequiredArgsConstructor
@Component
public class FsmOrderServiceImpl implements FsmOrderService {

	private final OrderMapper orderMapper;

    @Override
    public FsmOrder getFsmOrder(@NotNull String orderId) {
		Order order = orderMapper.selectOne(new LambdaQueryWrapper<Order>()
			.eq(Order::getOrderId, orderId));
		return Optional.ofNullable(order)
			.orElseThrow(() -> new ServiceException("not found order:" + orderId));
    }

}
