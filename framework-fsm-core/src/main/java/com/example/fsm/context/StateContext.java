package com.example.fsm.context;

import com.example.framework.common.util.CastUtil;
import com.example.fsm.FsmOrder;
import com.example.fsm.event.OrderStateEvent;
import lombok.Getter;
import lombok.Setter;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
@Setter
@Getter
public abstract class StateContext<C> {
	/**
	 * 订单操作事件
	 */
	private OrderStateEvent orderStateEvent;
	/**
	 * 状态机需要的订单基本信息
	 */
	private FsmOrder fsmOrder;
	/**
	 * 业务可定义的上下文泛型对象
	 */
	private C context;

	protected StateContext(OrderStateEvent orderStateEvent, FsmOrder fsmOrder) {
		this.orderStateEvent = orderStateEvent;
		this.fsmOrder = fsmOrder;
		this.context = CastUtil.safeFakeCast(this);
	}
}
