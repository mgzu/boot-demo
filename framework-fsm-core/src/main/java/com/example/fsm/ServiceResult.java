package com.example.fsm;

import lombok.*;

/**
 * 服务调用返回结果
 *
 * @author MaGuangZu
 * @since 2021-09-26
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResult<T> {
    private String message;
    private boolean success;
    private T data;

    public ServiceResult(boolean success) {
        this.success = success;
    }

    public ServiceResult(T data, String message) {
        this.data = data;
        this.message = message;
    }

}
