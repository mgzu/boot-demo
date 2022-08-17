package com.example.fsm.business.processor;

import com.example.app.common.constants.BizLogConstants;
import com.example.app.common.entity.Order;
import com.example.app.common.entity.bo.OrderBo;
import com.example.fsm.ServiceResult;
import com.example.fsm.annotation.OrderProcessor;
import com.example.fsm.business.checker.CreateParamChecker;
import com.example.fsm.business.context.CreateOrderContext;
import com.example.fsm.business.enums.*;
import com.example.fsm.business.mapstruct.OrderMapper;
import com.example.fsm.business.repository.OrderRepository;
import com.example.fsm.checker.Checkable;
import com.example.fsm.checker.Checker;
import com.example.fsm.checker.CheckerExecutor;
import com.example.fsm.context.StateContext;
import com.example.fsm.event.OrderStateEvent;
import com.example.fsm.plugin.PluginExecutor;
import com.example.fsm.processor.AbstractStateProcessor;
import com.mzt.logapi.starter.annotation.LogRecord;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@OrderProcessor(
        state = OrderStateEnum.INIT,
        event = OrderEventEnum.CREATE,
        bizCode = {
                BizCodeEnum.FBA,
                BizCodeEnum.CARGO,
        },
        sceneId = SceneIdEnum.H5
)
public class OrderCreatedProcessor extends AbstractStateProcessor<String, CreateOrderContext> {
    public OrderCreatedProcessor(CheckerExecutor checkerExecutor, PluginExecutor pluginExecutor,
                                 CreateParamChecker createParamChecker, OrderRepository orderRepository) {
        super(checkerExecutor, pluginExecutor);
        this.createParamChecker = createParamChecker;
        this.orderRepository = orderRepository;
    }

    private final CreateParamChecker createParamChecker;
    //    private UserChecker userChecker;
//    private UnFinishChecker unfinishChecker;
    private final OrderRepository orderRepository;

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
                return Collections.emptyList();
//                return Arrays.asList(userChecker, unfinishChecker);
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
        return new ServiceResult<>(true);
    }

    @LogRecord(success = "{{#context.context.orderInfo.orderId}} 下单成功", type = BizLogConstants.TYPE_ORDER, bizNo = "{{#context.context.orderInfo.orderId}}")
    @Override
    public ServiceResult<String> save(String nextState, StateContext<CreateOrderContext> context) throws Exception {
        OrderBo orderInfo = context.getContext().getOrderInfo();
        // 更新状态
        orderInfo.setOrderState(nextState);
        // 持久化
//        this.updateOrderInfo(orderInfo);
        Order order = OrderMapper.INSTANCE.toOrder(orderInfo);
        orderRepository.save(order);
        log.info("save BUSINESS order success, userId:{}, orderId:{}", orderInfo.getUserId(), order.getOrderId());
        return new ServiceResult<>(orderInfo.getOrderId(), "business下单成功");
    }

    @Override
    public void after(StateContext<CreateOrderContext> context) {

    }

    @Override
    public boolean filter(StateContext<CreateOrderContext> context) {
        OrderBo orderInfo = context.getContext().getOrderInfo();
        return orderInfo.getServiceType() == ServiceType.TAKEOFF_CAR;
    }

    /**
     * 促销相关扩展点
     */
    protected String doPromotion() {
        return "1";
    }
}
