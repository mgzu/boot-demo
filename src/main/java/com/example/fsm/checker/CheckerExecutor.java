package com.example.fsm.checker;


import com.example.fsm.ServiceResult;
import com.example.fsm.context.StateContext;
import com.example.fsm.exception.FsmException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 校验器的执行器
 */
@Slf4j
@Component
public class CheckerExecutor {

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * 执行并行校验器，
     * 按照任务投递的顺序判断返回。
     */
    public <T, C> ServiceResult<T> parallelCheck(List<Checker<T, C>> checkers, StateContext<C> context) {
        if (!CollectionUtils.isEmpty(checkers)) {
            if (checkers.size() == 1) {
                return checkers.get(0).check(context);
            }
            List<Future<ServiceResult<T>>> resultList = Collections.synchronizedList(new ArrayList<>(checkers.size()));
            checkers.sort(Comparator.comparingInt(Checker::order));
            for (Checker<T, C> c : checkers) {
                Future<ServiceResult<T>> future = executor.submit(() -> {
                    ServiceResult<T> result = c.check(context);
                    if (c.needRelease()) {
                        c.release(context, result);
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
                    throw new FsmException(e.getCause());
                }
            }
        }
        return new ServiceResult<>();
    }

    public <T, C> ServiceResult<T> serialCheck(List<Checker<T, C>> syncChecker, StateContext<C> context) {
        return null;
    }

    /**
     * 执行checker的释放操作
     */
    public <T, C> void releaseCheck(Checkable checkable, StateContext<C> context, ServiceResult<T> result) {
        List<Checker<T, C>> checkers = new ArrayList<>();
        checkers.addAll(checkable.getParamChecker());
        checkers.addAll(checkable.getSyncChecker());
        checkers.addAll(checkable.getAsyncChecker());
        checkers.removeIf(Checker::needRelease);
        if (!CollectionUtils.isEmpty(checkers)) {
            if (checkers.size() == 1) {
                checkers.get(0).release(context, result);
                return;
            }
            CountDownLatch latch = new CountDownLatch(checkers.size());
            for (Checker<T, C> c : checkers) {
                executor.execute(() -> {
                    try {
                        c.release(context, result);
                    } finally {
                        latch.countDown();
                    }
                });
            }
            try {
                latch.await();
            } catch (InterruptedException e) {
                log.error("releaseCheck latch.await error.", e);
                throw new FsmException(e.getCause());
            }
        }
    }
}
