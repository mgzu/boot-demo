package com.example.fsm.business.repository;

import com.example.app.common.entity.Order;
import com.example.framework.web.configure.jpa.repositories.TenantRepository;

/**
 * @author MaGuangZu
 * @since 2021-12-27
 */
public interface OrderRepository extends TenantRepository<Order> {
}
