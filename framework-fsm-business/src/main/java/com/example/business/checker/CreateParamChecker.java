package com.example.business.checker;

import com.example.business.context.CreateOrderContext;
import com.example.business.entity.bo.OrderBo;
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
