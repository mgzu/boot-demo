package com.example.fsm.processor;

import com.example.fsm.ServiceResult;
import com.example.fsm.checker.Checkable;
import com.example.fsm.checker.CheckerExecutor;
import com.example.fsm.context.StateActionStep;
import com.example.fsm.context.StateContext;
import com.example.fsm.exception.FsmException;
import com.example.fsm.plugin.PluginExecutor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * 状态机处理器抽象类
 */
@Getter
@Slf4j
public abstract class AbstractStateProcessor<T, C> implements StateProcessor<T, C>, StateActionStep<T, C> {

    protected AbstractStateProcessor(CheckerExecutor checkerExecutor, PluginExecutor pluginExecutor) {
        this.checkerExecutor = checkerExecutor;
        this.pluginExecutor = pluginExecutor;
    }

    private final CheckerExecutor checkerExecutor;

    private final PluginExecutor pluginExecutor;

    @NotNull
	@Override
    public final ServiceResult<T> action(@NotNull StateContext<C> context) throws FsmException {
        ServiceResult<T> result;
        Checkable checkable = this.getCheckable(context);
        try {
            // 参数校验器
            result = this.getCheckerExecutor().serialCheck(checkable.getParamChecker(), context);
            if (!result.isSuccess()) {
                return result;
            }
            // 数据准备
            this.prepare(context);
            // 串行校验器
            result = this.getCheckerExecutor().serialCheck(checkable.getSyncChecker(), context);
            if (!result.isSuccess()) {
                return result;
            }
            // 并行校验器
            result = this.getCheckerExecutor().parallelCheck(checkable.getAsyncChecker(), context);
            if (!result.isSuccess()) {
                return result;
            }
            // getNextState不能在prepare前，因为有的nextState是根据prepare中的数据转换而来
            String nextState = this.getNextState(context);
            // 业务逻辑
            result = this.action(nextState, context);
            if (!result.isSuccess()) {
                return result;
            }
            // 在action和save之间执行插件逻辑
            this.getPluginExecutor().parallelExecutor(context);
            // 持久化
            result = this.save(nextState, context);
            if (!result.isSuccess()) {
                return result;
            }
            // after
            this.after(context);
            return result;
        } catch (Exception e) {
            log.error("状态机处理器异常", e);
            throw new FsmException(e.getCause());
        }
    }

    public abstract boolean filter(StateContext<C> context);
}
