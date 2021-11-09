package com.example.fsm;

import lombok.Getter;
import lombok.Setter;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
@Setter
@Getter
public class ServiceResult<T> {
    private String message;
    private boolean success;

    public ServiceResult() {

    }

    public ServiceResult(T orderId, String message) {
        this.message = message;
    }
}
