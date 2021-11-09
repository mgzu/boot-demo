package com.example.fsm.engine;

import com.example.fsm.processor.AbstractStateProcessor;

import java.util.List;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
public interface StateProcessRegistry {
    List<AbstractStateProcessor> acquireStateProcess(String orderState, String eventType, String bizCode, String sceneId);
}
