package com.example.fsm.context;


import com.example.fsm.ServiceResult;
import com.example.fsm.checker.Checkable;

/**
 * 状态迁移动作处理步骤
 */
public interface StateActionStep<T, C> {
    /**
     * 准备数据
     */
    default void prepare(StateContext<C> context) {
    }

    Checkable getCheckable(StateContext<C> context);

    /**
     *
     */
    String getNextState(StateContext<C> context);

    /**
     * 状态动作方法，主要状态迁移逻辑
     */
    ServiceResult<T> action(String nextState, StateContext<C> context) throws Exception;

    /**
     * 状态数据持久化
     */
    ServiceResult<T> save(String nextState, StateContext<C> context) throws Exception;

    /**
     * 状态迁移成功，持久化后执行的后续处理
     */
    void after(StateContext<C> context);
}
