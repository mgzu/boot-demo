package com.example.demo.statemachine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * @author MaGuangZu
 * @since 2021-09-23
 */
@Slf4j
@WithStateMachine
public class MyBean {

    @OnTransition(target = "STATE1")
    public void toState1() {
        log.info("STATE1 event");
    }

    @OnTransition(target = "STATE2")
    public void toState2() {
        log.info("STATE2 event");
    }
}
