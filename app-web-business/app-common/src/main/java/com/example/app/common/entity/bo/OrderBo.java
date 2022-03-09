package com.example.app.common.entity.bo;

import com.example.app.common.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Setter
@Getter
public class OrderBo extends Order {

    private String userId;
    private int serviceType;

}
