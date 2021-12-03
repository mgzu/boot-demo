package com.example.fsm.processor;


import com.example.fsm.OrderInfo;
import com.example.fsm.ServiceResult;
import com.example.fsm.annotation.OrderProcessor;
import com.example.fsm.checker.*;
import com.example.fsm.context.CreateOrderContext;
import com.example.fsm.context.StateContext;
import com.example.fsm.enums.OrderEventEnum;
import com.example.fsm.enums.OrderStateEnum;
import com.example.fsm.enums.ServiceType;
import com.example.fsm.event.OrderStateEvent;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@OrderProcessor(state = OrderStateEnum.INIT, event = OrderEventEnum.CREATE, bizCode = {"CHEAP", "POPULAR"}, sceneId = "H5")
public class OrderCreatedProcessor extends AbstractStateProcessor<String, CreateOrderContext> {
    @Resource
    private CreateParamChecker createParamChecker;
    @Resource
    private UserChecker userChecker;
    @Resource
    private UnFinishChecker unfinishChecker;

    @Override
    public Checkable getCheckable(StateContext<CreateOrderContext> context) {
        return new Checkable() {
            @Override
            public List<Checker> getParamChecker() {
                return Arrays.asList(createParamChecker);
            }

            @Override
            public List<Checker> getSyncChecker() {
                return Collections.emptyList();
            }

            @Override
            public List<Checker> getAsyncChecker() {
                return Arrays.asList(userChecker, unfinishChecker);
            }
        };
    }

    @Override
    public String getNextState(StateContext<CreateOrderContext> context) {
        return OrderStateEnum.NEW;
    }

    @Override
    public ServiceResult<String> action(String nextState, StateContext<CreateOrderContext> context) throws Exception {
        CreateOrderContext createOrderContext = context.getContext();
        OrderStateEvent orderStateEvent = createOrderContext.getOrderStateEvent();
        // 促销信息信息
        String promotionInfo = this.doPromotion();
        return null;
    }

    @Override
    public ServiceResult<String> save(String nextState, StateContext<CreateOrderContext> context) throws Exception {
        OrderInfo orderInfo = context.getContext().getOrderInfo();
        // 更新状态
        orderInfo.setOrderState(nextState);
        // 持久化
//        this.updateOrderInfo(orderInfo);
        log.info("save BUSINESS order success, userId:{}, orderId:{}", orderInfo.getUserId(), orderInfo.getOrderId());
        return new ServiceResult<>(orderInfo.getOrderId(), "business下单成功");
    }

    @Override
    public void after(StateContext<CreateOrderContext> context) {

    }

    @Override
    public boolean filter(StateContext<CreateOrderContext> context) {
        OrderInfo orderInfo = (OrderInfo) context.getFsmOrder();
        return orderInfo.getServiceType() == ServiceType.TAKEOFF_CAR;
    }

    /**
     * 促销相关扩展点
     */
    protected String doPromotion() {
        return "1";
    }
}
