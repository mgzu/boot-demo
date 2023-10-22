package com.example.app.order.checker;

import com.example.app.common.entity.bo.OrderBo;
import com.example.app.order.context.CreateOrderContext;
import com.example.fsm.ServiceResult;
import com.example.fsm.checker.Checker;
import com.example.fsm.context.StateContext;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
@Component
public class CreateParamChecker implements Checker<OrderBo, CreateOrderContext> {
    @NotNull
    @Override
    public ServiceResult<OrderBo> check(StateContext<CreateOrderContext> context) {
        ServiceResult<OrderBo> result = new ServiceResult<>();
        result.setData(context.getContext().getOrderInfo());
        result.setSuccess(true);
        return result;
    }
}
