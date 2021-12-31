package com.example.fsm.business.repository.impl;

import com.example.app.order.entity.Order;
import com.example.fsm.business.mapstruct.OrderMapper;
import com.example.fsm.business.repository.OrderRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * @author MaGuangZu
 * @since 2021-12-27
 */
@Repository
public class OrderRepositoryImpl extends SimpleJpaRepository<Order, String> implements OrderRepository {

    public OrderRepositoryImpl(final EntityManager em) {
        super(Order.class, em);
    }

    /**
     * 这里的实现跟 JPA 默认的行为可能有一定的出入，例如（自增返回 ID，原 entity 未自动填写 ID）
     */
    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public <S extends Order> S save(@NotNull S entity) {
        Order order = OrderMapper.INSTANCE.toOrder(entity);
        return (S) super.save(order);
    }
}
