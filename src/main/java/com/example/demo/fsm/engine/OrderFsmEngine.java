package com.example.demo.fsm.engine;


import com.example.demo.fsm.FsmOrder;
import com.example.demo.fsm.ServiceResult;
import com.example.demo.fsm.event.OrderStateEvent;

/**
 * 状态机执行引擎
 */
public interface OrderFsmEngine {
    /**
     * 执行状态迁移事件，不传FsmOrder默认会根据orderId从FsmOrderService接口获取
     */
    <T> ServiceResult<T> sendEvent(OrderStateEvent orderStateEvent) throws Exception;

    /**
     * 执行状态迁移事件，可携带FsmOrder参数
     */
    <T> ServiceResult<T> sendEvent(OrderStateEvent orderStateEvent, FsmOrder fsmOrder) throws Exception;
}
