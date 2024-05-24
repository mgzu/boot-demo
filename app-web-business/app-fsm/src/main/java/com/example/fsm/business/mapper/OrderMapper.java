package com.example.fsm.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.app.common.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author MaGuangZu
 * @since 2024-05-24
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
