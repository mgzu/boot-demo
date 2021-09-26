package com.example.demo;

import com.example.demo.statemachine.WaybillEvents;
import com.example.demo.statemachine.WaybillStates;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;

import java.util.EnumSet;

@SpringBootTest
class DemoApplicationTests {

    public StateMachine<WaybillStates, WaybillEvents> buildMachine() throws Exception {
        StateMachineBuilder.Builder<WaybillStates, WaybillEvents> builder = StateMachineBuilder.builder();

        builder.configureStates()
                .withStates()
                .initial(WaybillStates.STATE1)
                .states(EnumSet.allOf(WaybillStates.class));

        builder.configureTransitions()
                .withExternal()
                .source(WaybillStates.STATE1).target(WaybillStates.STATE2)
                .event(WaybillEvents.EVENT1)
                .and()
                .withExternal()
                .source(WaybillStates.STATE2).target(WaybillStates.STATE1)
                .event(WaybillEvents.EVENT2);

        return builder.build();
    }

    @Test
    public void contextLoads() throws Exception {
        StateMachine<WaybillStates, WaybillEvents> stateMachine = buildMachine();
    }

}
