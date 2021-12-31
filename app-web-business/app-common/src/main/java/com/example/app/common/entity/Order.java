package com.example.app.order.entity;

import com.example.framework.web.entity.AuditableEntity;
import com.example.fsm.FsmOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

/**
 * @author MaGuangZu
 * @since 2021-12-24
 */
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Setter
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity(name = "t_order")
public class Order extends AuditableEntity implements FsmOrder {

    @NotNull
    private String orderState;

    @Transient
    @NotNull
    private String bizCode;
    @Transient
    @NotNull
    private String sceneId;

    @Override
    public String getOrderId() {
        return this.getId();
    }
}
