package com.example.demo.business.entity.bo;

import com.example.demo.business.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Setter
@Getter
@MappedSuperclass
@Entity(name = "t_order")
public class OrderBo extends Order {

    @Transient
    private String userId;
    @Transient
    private int serviceType;

}
