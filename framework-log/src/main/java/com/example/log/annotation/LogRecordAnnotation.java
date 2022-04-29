package com.example.log.annotation;

import java.lang.annotation.*;

/**
 * @author MaGuangZu
 * @since 2022-04-29
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface LogRecordAnnotation {
    String success();

    String fail() default "";

    String operator() default "";

    String bizNo();

    String category() default "";

    String detail() default "";

    String condition() default "";
}
