package com.example.fsm.processor;

import com.example.fsm.ServiceResult;
import com.example.fsm.context.StateContext;
import com.example.fsm.exception.FsmException;

/**
 * 状态机处理器接口
 */
public interface StateProcessor<T, C> {
    /**
     * 执行状态迁移的入口
     */
    ServiceResult<T> action(StateContext<C> context) throws FsmException;
}
