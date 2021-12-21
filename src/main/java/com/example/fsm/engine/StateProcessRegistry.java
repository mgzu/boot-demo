package com.example.fsm.engine;

import com.example.fsm.processor.AbstractStateProcessor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
public interface StateProcessRegistry {
    @NotNull
    List<AbstractStateProcessor> acquireStateProcess(String orderState, String eventType, String bizCode, String sceneId);
}
