package com.example.app.order.checker;

import com.example.app.order.context.CreateOrderContext;
import com.example.fsm.ServiceResult;
import com.example.fsm.checker.Checker;
import com.example.fsm.context.StateContext;
import org.jetbrains.annotations.NotNull;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
//@Component
public class UnFinishChecker implements Checker<Void, CreateOrderContext> {
    @NotNull
    @Override
    public ServiceResult<Void> check(StateContext<CreateOrderContext> context) {
        return new ServiceResult<>(true);
    }
}
