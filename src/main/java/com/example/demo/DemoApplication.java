package com.example.demo;

import com.example.demo.statemachine.WaybillEvents;
import com.example.demo.statemachine.WaybillStates;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.EnumSet;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public StateMachine<WaybillStates, WaybillEvents> stateMachineTarget() throws Exception {
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

}
