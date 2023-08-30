package com.example.framework.web.entity;

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

    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 状态码
     */
    private int code;
    /**
     * 消息
     */
    private String message;
    /**
     * 数据
     */
    private T data;

    public static <T> Result<T> ok() {
		return Result.<T>builder()
			.success(true)
			.message(HttpStatus.OK.getReasonPhrase())
			.code(HttpStatus.OK.value())
			.build();
    }

    public static <T> Result<T> ok(T data) {
		return Result.<T>builder()
			.success(true)
			.message(HttpStatus.OK.getReasonPhrase())
			.code(HttpStatus.OK.value())
			.data(data)
			.build();
	}

	public static <T> Result<T> error() {
		return Result.<T>builder()
			.success(false)
			.message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
			.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.build();
	}

    public static <T> Result<T> error(String message) {
		return Result.<T>builder()
			.success(false)
			.message(message)
			.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.build();
    }

    public static <T> Result<T> error(T data, String message) {
		return Result.<T>builder()
			.success(false)
			.message(message)
			.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.data(data)
			.build();
    }

}
