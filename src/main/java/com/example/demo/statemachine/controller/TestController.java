package com.example.demo.statemachine.controller;

import com.example.demo.statemachine.WaybillEvents;
import com.example.demo.statemachine.WaybillStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MaGuangZu
 * @since 2021-09-24
 */
@RestController
public class TestController {

    @Autowired
    private StateMachine<WaybillStates, WaybillEvents> stateMachine;

    @GetMapping("/test1")
    public ResponseEntity<String> test1() {
        stateMachine.sendEvent(WaybillEvents.EVENT1);
        return ResponseEntity.ok("OJBK");
    }

    @GetMapping("/test2")
    public ResponseEntity<String> test2() {
        stateMachine.sendEvent(WaybillEvents.EVENT2);
        return ResponseEntity.ok("OJBK");
    }
}
