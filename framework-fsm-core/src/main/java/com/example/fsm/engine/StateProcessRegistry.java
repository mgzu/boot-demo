package com.example.fsm.engine;

import com.example.fsm.processor.AbstractStateProcessor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 状态处理器注册器
 *
 * @author MaGuangZu
 * @since 2021-09-26
 */
public interface StateProcessRegistry {

    /**
     * 根据条件获取状态处理器
     *
     * @param orderState 订单状态
     * @param eventType  事件类型
     * @param bizCode    业务代码
     * @param sceneId    场景ID
     * @return 符合条件的处理器
     */
    @NotNull
    List<AbstractStateProcessor<?, ?>> acquireStateProcess(String orderState, String eventType, String bizCode, String sceneId);
}
