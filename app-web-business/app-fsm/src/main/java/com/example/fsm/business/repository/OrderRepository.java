package com.example.fsm.business.repository;

import com.example.app.common.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author MaGuangZu
 * @since 2021-12-27
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}
