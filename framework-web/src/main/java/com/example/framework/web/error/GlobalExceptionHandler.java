package com.example.framework.web.error;

import com.example.framework.web.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author MaGuangZu
 * @since 2022-12-30
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public Result<Void> throwable(Throwable e) {
        String localizedMessage = e.getLocalizedMessage();
        log.error(localizedMessage, e);
		return Result.error();
    }

    @ExceptionHandler({Exception.class})
    public Result<Void> exception(Exception e) {
        String localizedMessage = e.getLocalizedMessage();
        log.error(localizedMessage, e);
		return Result.error();
    }

	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
	public Result<Void> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		return Result.<Void>builder()
			.success(false)
			.message(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase())
			.code(HttpStatus.METHOD_NOT_ALLOWED.value())
			.build();
	}

}
