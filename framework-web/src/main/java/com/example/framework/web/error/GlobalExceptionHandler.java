package com.example.framework.web.error;

import com.example.framework.common.exceptions.ServiceException;
import com.example.framework.web.entity.Result;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.annotation.AnnotationUtil;
import org.dromara.hutool.core.reflect.FieldUtil;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.lang.reflect.Field;

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

	@ExceptionHandler({ServiceException.class})
	public Result<Void> exception(ServiceException e) {
		return Result.error(e.getLocalizedMessage());
	}

	@ExceptionHandler({MethodArgumentNotValidException.class})
	public Result<Void> exception(MethodArgumentNotValidException e) {
		BindingResult bindingResult = e.getBindingResult();
		StringBuilder errorMessage = new StringBuilder();
		for (ObjectError error : bindingResult.getGlobalErrors()) {
			errorMessage.append(error.getDefaultMessage()).append(";");
		}
		Object target = bindingResult.getTarget();
		if (target != null) {
			Class<?> clazz = target.getClass();
			for (FieldError error : bindingResult.getFieldErrors()) {
				Field field = FieldUtil.getField(clazz, error.getField());
				SchemaProperty schemaProperty = AnnotationUtil.getAnnotation(field, SchemaProperty.class);
				if (schemaProperty != null) {
					errorMessage.append(schemaProperty.name());
				} else {
					errorMessage.append(error.getField());
				}
				errorMessage.append(":").append(error.getDefaultMessage()).append(";");
			}
		}
		return Result.<Void>builder()
			.code(HttpStatus.BAD_REQUEST.value())
			.message(errorMessage.toString())
			.build();
	}

	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
	public Result<Void> httpRequestMethodNotSupportedException() {
		return Result.<Void>builder()
			.message(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase())
			.code(HttpStatus.METHOD_NOT_ALLOWED.value())
			.build();
	}

	@ExceptionHandler({HttpMediaTypeNotSupportedException.class})
	public Result<Void> httpMediaTypeNotSupportedException() {
		return Result.<Void>builder()
			.message(HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase())
			.code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
			.build();
	}

	@ExceptionHandler({NoResourceFoundException.class})
	public Result<Void> noResourceFoundException() {
		return Result.<Void>builder()
			.code(HttpStatus.NOT_FOUND.value())
			.message(HttpStatus.NOT_FOUND.getReasonPhrase())
			.build();
	}

}
