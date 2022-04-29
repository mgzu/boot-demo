package com.example.log.autoconfigure;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author MaGuangZu
 * @since 2022-04-29
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LogRecordConfigureSelector.class)
public @interface EnableLogRecord {

    String tenant();

    AdviceMode mode() default AdviceMode.PROXY;
}
