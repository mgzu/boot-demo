package com.example.demo.fsm.processor;

import com.example.demo.fsm.ServiceResult;
import com.example.demo.fsm.context.StateContext;
import com.example.demo.fsm.annotation.StateActionStep;
import com.example.demo.fsm.checker.Checkable;
import com.example.demo.fsm.checker.CheckerExecutor;
import com.example.demo.fsm.plugin.PluginExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 状态机处理器模板类
 */
@Component
public abstract class AbstractStateProcessor<T, C> implements StateProcessor<T, C>, StateActionStep<T, C> {
    @Resource
    private CheckerExecutor checkerExecutor;
    @Resource
    private PluginExecutor pluginExecutor;

    @Override
    public final ServiceResult<T> action(StateContext<C> context) throws Exception {
        ServiceResult<T> result;
        Checkable checkable = this.getCheckable(context);
        try {
            // 参数校验器
            result = checkerExecutor.serialCheck(checkable.getParamChecker(), context);
            if (!result.isSuccess()) {
                return result;
            }
            // 数据准备
            this.prepare(context);
            // 串行校验器
            result = checkerExecutor.serialCheck(checkable.getSyncChecker(), context);
            if (!result.isSuccess()) {
                return result;
            }
            // 并行校验器
            result = checkerExecutor.parallelCheck(checkable.getAsyncChecker(), context);
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
            this.pluginExecutor.parallelExecutor(context);
            // 持久化
            result = this.save(nextState, context);
            if (!result.isSuccess()) {
                return result;
            }
            // after
            this.after(context);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    public abstract boolean filter(StateContext<C> context);
}
