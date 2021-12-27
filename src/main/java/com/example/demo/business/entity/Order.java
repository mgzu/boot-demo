package com.example.demo.business.entity;

import com.example.fsm.FsmOrder;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

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
public class Order implements FsmOrder {

    @Id
    @Column(nullable = false)
    @NotNull
    private String orderId;

    @NotNull
    private String orderState;

    @Transient
    @NotNull
    private String bizCode;
    @Transient
    @NotNull
    private String sceneId;

}
