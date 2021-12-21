package com.example.fsm;

import lombok.Builder;

/**
 * 服务调用返回结果
 *
 * @author MaGuangZu
 * @since 2021-09-26
 */
@Builder
public class ServiceResult<T> {
    private String message;
    private boolean success;
    private T data;

    public ServiceResult() {

    }

    public ServiceResult(boolean success) {
        this.success = success;
    }

    public ServiceResult(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
