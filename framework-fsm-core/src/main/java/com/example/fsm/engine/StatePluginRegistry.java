package com.example.fsm.engine;

import com.example.fsm.plugin.PluginHandler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 插件注册器
 *
 * @author MaGuangZu
 * @since 2021-12-30
 */
public interface StatePluginRegistry {

	/**
	 * 根据条件获取插件
	 *
	 * @param orderState 订单状态
	 * @param eventType  事件类型
	 * @param bizCode    业务代码
	 * @param sceneId    场景ID
	 * @return 符合条件的插件列表
	 */
	@NotNull
	<T, C> List<PluginHandler<T, C>> acquireStatePlugin(String orderState, String eventType, String bizCode, String sceneId);

}
