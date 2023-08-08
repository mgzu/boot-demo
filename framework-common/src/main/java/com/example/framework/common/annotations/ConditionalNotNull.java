package com.example.framework.common.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author MaGuangZu
 * @since 2023-07-28
 */
@Target({FIELD})
@Retention(RUNTIME)
@Documented
public @interface ConditionalNotNull {

	String value();

	/**
	 * if blank, use {@link ConditionalNotNullValid#message}
	 */
	String message() default "";

}
