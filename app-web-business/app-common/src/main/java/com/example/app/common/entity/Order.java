package com.example.app.common.entity;

import com.example.framework.web.entity.BaseEntity;
import com.example.fsm.FsmOrder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Entity(name = "t_order")
public class Order extends BaseEntity implements FsmOrder {

	private String orderId;

	@NotNull
	@Column(nullable = false)
	private String orderState;

	@NotNull
	@Column(nullable = false)
	private String bizCode;

	@NotNull
	@Column(nullable = false)
	private String sceneId;

}
