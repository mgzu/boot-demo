package com.example.log.service;

import com.example.log.entity.Operator;

/**
 * @author MaGuangZu
 * @since 2022-04-29
 */
public interface IOperatorGetService {
    /**
     * 可以在里面外部的获取当前登陆的用户，比如 UserContext.getCurrentUser()
     *
     * @return 转换成Operator返回
     */
    Operator getUser();
}
