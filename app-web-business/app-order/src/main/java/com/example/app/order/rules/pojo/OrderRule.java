package com.example.app.order.rules.pojo;

import com.example.app.order.constants.RuleConstants;
import org.jeasy.rules.annotation.*;

/**
 * @author MaGuangZu
 * @since 2022-10-25
 */
@Rule(name = RuleConstants.ORDER)
public class OrderRule {

    @Condition
    public boolean when(@Fact("number") int number) {
        return number % 3 == 0;
    }

    @Action
    public void then(@Fact("number") int number) {
        System.out.print(number + " is three");
    }

    @Priority
    public int getPriority() {
        return 1;
    }

}
