package com.example.fsm.checker;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * 状态机校验器
 */
public interface Checkable {
	/**
	 * 参数校验
	 */
	@NotNull
	default <T, C> List<Checker<T, C>> getParamChecker() {
		return Collections.emptyList();
	}

	/**
	 * 需同步执行的状态检查器
	 */
	@NotNull
	default <T, C> List<Checker<T, C>> getSyncChecker() {
		return Collections.emptyList();
	}

	/**
	 * 可异步执行的校验器
	 */
	@NotNull
	default <T, C> List<Checker<T, C>> getAsyncChecker() {
		return Collections.emptyList();
	}
}
