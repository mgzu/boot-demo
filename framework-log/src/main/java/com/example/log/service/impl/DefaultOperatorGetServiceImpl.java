package com.example.log.service.impl;

import com.example.log.entity.Operator;
import com.example.log.service.IOperatorGetService;

/**
 * @author MaGuangZu
 * @since 2022-04-29
 */
public class DefaultOperatorGetServiceImpl implements IOperatorGetService {
    @Override
    public Operator getUser() {
        return new Operator();
    }
}
