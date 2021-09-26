package com.example.demo.fsm.engine;

import com.example.demo.fsm.processor.AbstractStateProcessor;

import java.util.List;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
public interface StateProcessRegistry {
    List<AbstractStateProcessor> acquireStateProcess(String orderState, String eventType, String bizCode, String sceneId);
}
