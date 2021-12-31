package com.example.framework.web;

import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * @author MaGuangZu
 * @since 2021-09-27
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Result<T> {
    private boolean success;
    private int code;
    private String message;
    private T data;

    public static <T> Result<T> ok() {
        return Result.<T>builder().success(true).message(HttpStatus.OK.getReasonPhrase()).code(HttpStatus.OK.value()).build();
    }

    public static <T> Result<T> ok(T data) {
        return Result.<T>builder().success(true).message(HttpStatus.OK.getReasonPhrase()).code(HttpStatus.OK.value()).data(data).build();
    }

}
