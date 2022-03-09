package com.example.fsm.annotation;

import java.lang.annotation.*;

/**
 * @author MaGuangZu
 * @since 2022-03-09
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FsmEngine {
    String scanBasePackage() default "";
}
