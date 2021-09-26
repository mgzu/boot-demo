package com.example.demo.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

/**
 * @author MaGuangZu
 * @since 2021-09-24
 */
@Component
public class CommonRunner implements ApplicationRunner {

    @Autowired
    private StateMachine<WaybillStates, WaybillEvents> stateMachine;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        stateMachine.start();
    }

}
