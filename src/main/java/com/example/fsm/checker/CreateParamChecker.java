package com.example.fsm.checker;

import com.example.fsm.OrderInfo;
import com.example.fsm.ServiceResult;
import com.example.fsm.context.CreateOrderContext;
import com.example.fsm.context.StateContext;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
@Component
public class CreateParamChecker implements Checker<OrderInfo, CreateOrderContext> {
    @NotNull
    @Override
    public ServiceResult<OrderInfo> check(StateContext<CreateOrderContext> context) {
        ServiceResult<OrderInfo> result = new ServiceResult<>();
        result.setData(context.getContext().getOrderInfo());
        result.setSuccess(true);
        return result;
    }
}
