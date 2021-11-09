package com.example.fsm.checker;

import java.util.Collections;
import java.util.List;

/**
 * 状态机校验器
 */
public interface Checkable {
    /**
     * 参数校验
     */
    default <T, C> List<Checker<T, C>> getParamChecker() {
        return Collections.emptyList();
    }

    /**
     * 需同步执行的状态检查器
     */
    default <T, C> List<Checker<T, C>> getSyncChecker() {
        return Collections.emptyList();
    }

    /**
     * 可异步执行的校验器
     */
    default <T, C> List<Checker<T, C>> getAsyncChecker() {
        return Collections.emptyList();
    }
}
