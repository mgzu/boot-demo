package com.example.fsm.business.processor;

import com.example.app.common.entity.Order;
import com.example.fsm.FsmOrder;
import com.example.fsm.ServiceResult;
import com.example.fsm.annotation.OrderProcessor;
import com.example.fsm.business.context.ReturnOrderContext;
import com.example.fsm.business.enums.BizCodeEnum;
import com.example.fsm.business.enums.OrderEventEnum;
import com.example.fsm.business.enums.OrderStateEnum;
import com.example.fsm.business.enums.SceneIdEnum;
import com.example.fsm.business.mapstruct.OrderMapper;
import com.example.fsm.business.repository.OrderRepository;
import com.example.fsm.checker.Checkable;
import com.example.fsm.checker.Checker;
import com.example.fsm.context.StateContext;
import com.example.fsm.event.OrderStateEvent;
import com.example.fsm.processor.AbstractStateProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

@Slf4j
@OrderProcessor(state = OrderStateEnum.INIT, event = OrderEventEnum.RETURN, bizCode = {BizCodeEnum.FBA, BizCodeEnum.CARGO,}, sceneId = SceneIdEnum.H5)
public class OrderReturnProcessor extends AbstractStateProcessor<String, ReturnOrderContext> {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Checkable getCheckable(StateContext<ReturnOrderContext> context) {
        return new Checkable() {
            @Override
            public List<Checker> getParamChecker() {
                return Collections.emptyList();
            }

            @Override
            public List<Checker> getSyncChecker() {
                return Collections.emptyList();
            }

            @Override
            public List<Checker> getAsyncChecker() {
                return Collections.emptyList();
//                return Arrays.asList(userChecker, unfinishChecker);
            }
        };
    }

    @Override
    public String getNextState(StateContext<ReturnOrderContext> context) {
        return OrderStateEnum.NEW;
    }

    @Override
    public ServiceResult<String> action(String nextState, StateContext<ReturnOrderContext> context) throws Exception {
        ReturnOrderContext orderContext = context.getContext();
        OrderStateEvent orderStateEvent = orderContext.getOrderStateEvent();
        // 促销信息信息
        String promotionInfo = this.doPromotion();
        return new ServiceResult<>(true);
    }

    @Override
    public ServiceResult<String> save(String nextState, StateContext<ReturnOrderContext> context) throws Exception {
        FsmOrder orderInfo = context.getContext().getFsmOrder();
        // 更新状态
        Order order = OrderMapper.INSTANCE.toOrder(orderInfo);
        // 持久化
//        this.updateOrderInfo(orderInfo);
        orderRepository.save(order);
        log.info("save BUSINESS order success, orderId:{}", order.getOrderId());
        return new ServiceResult<>(orderInfo.getOrderId(), "business退货成功");
    }

    @Override
    public void after(StateContext<ReturnOrderContext> context) {

    }

    @Override
    public boolean filter(StateContext<ReturnOrderContext> context) {
        return true;
    }

    /**
     * 促销相关扩展点
     */
    protected String doPromotion() {
        return "1";
    }
}
