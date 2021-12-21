package com.example.fsm.engine;


import com.example.fsm.FsmOrder;
import com.example.fsm.ServiceResult;
import com.example.fsm.event.OrderStateEvent;
import org.jetbrains.annotations.NotNull;

/**
 * 状态机执行引擎
 */
public interface OrderFsmEngine {
    /**
     * 执行状态迁移事件，不传FsmOrder默认会根据orderId从FsmOrderService接口获取
     */
    @NotNull
    <T> ServiceResult<T> sendEvent(@NotNull OrderStateEvent orderStateEvent) throws Exception;

    /**
     * 执行状态迁移事件，可携带FsmOrder参数
     */
    @NotNull
    <T> ServiceResult<T> sendEvent(@NotNull OrderStateEvent orderStateEvent,
                                   @NotNull FsmOrder fsmOrder) throws Exception;
}
