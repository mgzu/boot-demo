package com.example.app.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.framework.web.entity.BaseEntity;
import com.example.fsm.FsmOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * @author MaGuangZu
 * @since 2021-12-24
 */
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Setter
@Getter
@TableName("t_order")
public class Order extends BaseEntity implements FsmOrder {

	private String orderId;

	@NotNull
	private String orderState;

	@NotNull
	private String bizCode;

	@NotNull
	private String sceneId;

}
