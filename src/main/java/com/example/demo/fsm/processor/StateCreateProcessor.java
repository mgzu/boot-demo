package com.example.demo.fsm.processor;


import com.example.demo.fsm.ServiceResult;
import com.example.demo.fsm.context.StateContext;
import com.example.demo.fsm.annotation.OrderProcessor;
import com.example.demo.fsm.enums.OrderEventEnum;
import com.example.demo.fsm.enums.OrderStateEnum;

/**
 * 创建订单状态对应的状态处理器
 */
@OrderProcessor(state = OrderStateEnum.INIT, event = OrderEventEnum.CREATE, bizCode = {"CHEAP", "POPULAR"}, sceneId = "H5")
public class StateCreateProcessor implements StateProcessor {
    @Override
    public ServiceResult action(StateContext context) throws Exception {
        return null;
    }
}
