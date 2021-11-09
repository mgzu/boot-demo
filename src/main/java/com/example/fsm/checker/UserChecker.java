package com.example.fsm.checker;

import com.example.fsm.ServiceResult;
import com.example.fsm.context.StateContext;
import org.springframework.stereotype.Component;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
@Component
public class UserChecker implements Checker {
    @Override
    public ServiceResult check(StateContext context) {
        return null;
    }
}
