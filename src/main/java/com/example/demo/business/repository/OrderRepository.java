package com.example.demo.business.repository;

import com.example.demo.business.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author MaGuangZu
 * @since 2021-12-27
 */
@NoRepositoryBean
public interface OrderRepository extends JpaRepository<Order, String> {
}