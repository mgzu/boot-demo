package com.example.demo.fsm.processor;

import com.example.demo.fsm.ServiceResult;
import com.example.demo.fsm.context.StateContext;

/**
 * 状态机处理器接口
 */
public interface StateProcessor<T, C> {
    /**
     * 执行状态迁移的入口
     */
    ServiceResult<T> action(StateContext<C> context) throws Exception;
}
