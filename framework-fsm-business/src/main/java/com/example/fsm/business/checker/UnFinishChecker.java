package com.example.fsm.business.checker;

import com.example.fsm.ServiceResult;
import com.example.fsm.checker.Checker;
import com.example.fsm.context.StateContext;
import org.jetbrains.annotations.NotNull;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
//@Component
public class UnFinishChecker implements Checker {
    @NotNull
    @Override
    public ServiceResult check(StateContext context) {
        return null;
    }
}
