package com.example.demo.statemachine;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @author MaGuangZu
 * @since 2021-09-23
 */
@Configuration
@EnableStateMachine
public class Config extends EnumStateMachineConfigurerAdapter<WaybillStates, WaybillEvents> {

    @Override
    public void configure(StateMachineStateConfigurer<WaybillStates, WaybillEvents> states)
            throws Exception {
        states
                .withStates()
                .initial(WaybillStates.STATE1)
                .states(EnumSet.allOf(WaybillStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<WaybillStates, WaybillEvents> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(WaybillStates.STATE1).target(WaybillStates.STATE2)
                .event(WaybillEvents.EVENT1)
                .and()
                .withExternal()
                .source(WaybillStates.STATE2).target(WaybillStates.STATE1)
                .event(WaybillEvents.EVENT2);
    }
}
