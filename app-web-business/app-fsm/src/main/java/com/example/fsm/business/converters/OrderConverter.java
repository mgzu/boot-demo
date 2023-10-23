package com.example.fsm.business.converters;

import com.example.app.common.entity.Order;
import com.example.app.common.entity.bo.OrderBo;
import com.example.fsm.FsmOrder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author MaGuangZu
 * @since 2021-12-27
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderConverter {

    OrderConverter INSTANCE = Mappers.getMapper(OrderConverter.class);

    Order toOrder(FsmOrder order);

    Order toOrder(OrderBo order);
}
