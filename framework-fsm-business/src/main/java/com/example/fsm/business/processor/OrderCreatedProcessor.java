package com.example.fsm.business.processor;

import com.example.fsm.ServiceResult;
import com.example.fsm.annotation.OrderProcessor;
import com.example.fsm.business.checker.CreateParamChecker;
import com.example.fsm.business.context.CreateOrderContext;
import com.example.fsm.business.entity.bo.OrderBo;
import com.example.fsm.business.enums.*;
import com.example.fsm.business.repository.OrderRepository;
import com.example.fsm.checker.Checkable;
import com.example.fsm.checker.Checker;
import com.example.fsm.context.StateContext;
import com.example.fsm.event.OrderStateEvent;
import com.example.fsm.processor.AbstractStateProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Autowired
    private CreateParamChecker createParamChecker;
    //    @Resource
//    private UserChecker userChecker;
//    @Resource
//    private UnFinishChecker unfinishChecker;
    @Autowired
    private OrderRepository orderInfoRepository;

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

    @Override
    public ServiceResult<String> save(String nextState, StateContext<CreateOrderContext> context) throws Exception {
        OrderBo orderInfo = context.getContext().getOrderInfo();
        // 更新状态
        orderInfo.setOrderState(nextState);
        // 持久化
//        this.updateOrderInfo(orderInfo);
        orderInfoRepository.save(orderInfo);
        log.info("save BUSINESS order success, userId:{}, orderId:{}", orderInfo.getUserId(), orderInfo.getOrderId());
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
