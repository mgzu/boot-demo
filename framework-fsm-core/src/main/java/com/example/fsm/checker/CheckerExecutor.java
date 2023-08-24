package com.example.fsm.checker;


import com.example.fsm.ServiceResult;
import com.example.fsm.context.StateContext;
import com.example.fsm.exception.FsmException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 校验器的执行器
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class CheckerExecutor {

	private final ExecutorService executor = Executors.newSingleThreadExecutor();

	/**
	 * 执行并行校验器，
	 * 按照任务投递的顺序判断返回。
	 */
	@SuppressWarnings("java:S3776")
	public <T, C> ServiceResult<T> parallelCheck(List<Checker<T, C>> checkers, StateContext<C> context) {
		if (!CollectionUtils.isEmpty(checkers)) {
			if (checkers.size() == 1) {
				return checkers.get(0).check(context);
			}
			List<Future<ServiceResult<T>>> resultList = Collections.synchronizedList(new ArrayList<>(checkers.size()));
			checkers.sort(Comparator.comparingInt(Checker::order));
			for (Checker<T, C> checker : checkers) {
				Future<ServiceResult<T>> future = executor.submit(() -> {
					ServiceResult<T> result = checker.check(context);
					if (checker.needRelease()) {
						checker.release(context, result);
					}
					return result;
				});
				resultList.add(future);
			}
			for (Future<ServiceResult<T>> future : resultList) {
				try {
					ServiceResult<T> sr = future.get();
					if (!sr.isSuccess()) {
						return sr;
					}
				} catch (Exception e) {
					log.error("parallelCheck executor.submit error.", e);
					Thread.currentThread().interrupt();
					throw new FsmException(e.getCause());
				}
			}
		}
		return new ServiceResult<>(true);
	}

	/**
	 * 执行同步校验器，
	 * 按照任务投递的顺序判断返回。
	 */
	public <T, C> ServiceResult<T> serialCheck(List<Checker<T, C>> syncChecker, StateContext<C> context) {
		if (!CollectionUtils.isEmpty(syncChecker)) {
			List<ServiceResult<T>> resultList = Collections.synchronizedList(new ArrayList<>(syncChecker.size()));
			try {
				for (Checker<T, C> c : syncChecker) {
					executor.execute(() -> {
						ServiceResult<T> result = c.check(context);
						if (c.needRelease()) {
							c.release(context, result);
						}
						resultList.add(result);
					});
				}
			} catch (Exception e) {
				log.error("parallelCheck executor.submit error.", e);
				throw new FsmException(e.getCause());
			}
			for (ServiceResult<T> result : resultList) {
				if (!result.isSuccess()) {
					return result;
				}
			}
		}
		return new ServiceResult<>(true);
	}

}
