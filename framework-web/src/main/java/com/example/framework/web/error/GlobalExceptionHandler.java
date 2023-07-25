package com.example.framework.web.error;

import com.example.framework.web.entity.Result;
import lombok.extern.slf4j.Slf4j;
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
        return Result.error(localizedMessage);
    }

    @ExceptionHandler({Exception.class})
    public Result<Void> exception(Exception e) {
        String localizedMessage = e.getLocalizedMessage();
        log.error(localizedMessage, e);
        return Result.error(localizedMessage);
    }

}
