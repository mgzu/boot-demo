package com.example.app.common.entity;

import com.example.framework.web.entity.AuditableEntity;
import com.example.fsm.FsmOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Transient;
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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity(name = "t_order")
public class Order extends AuditableEntity implements FsmOrder {

	private String orderId;

	@NotNull
	private String orderState;

	@NotNull
	private String bizCode;

	@NotNull
	private String sceneId;

	@Transient
	@JsonIgnore
	@Override
	public boolean isNew() {
		return this.getOrderState().equals("NEW");
	}
}
