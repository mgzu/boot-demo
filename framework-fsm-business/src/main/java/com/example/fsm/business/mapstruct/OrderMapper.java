package com.example.fsm.business.mapstruct;

import com.example.fsm.business.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author MaGuangZu
 * @since 2021-12-27
 */
@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order toOrder(Order order);
}
