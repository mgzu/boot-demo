package com.example.fsm.checker;

import com.example.fsm.ServiceResult;
import com.example.fsm.context.CreateOrderContext;
import com.example.fsm.context.StateContext;
import org.springframework.stereotype.Component;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
@Component
public class CreateParamChecker implements Checker<T, CreateOrderContext> {
    @Override
    public <T> ServiceResult<T> check(StateContext<CreateOrderContext> context) {
        return null;
    }
}
