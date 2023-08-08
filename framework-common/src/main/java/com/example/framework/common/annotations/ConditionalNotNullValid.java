package com.example.framework.common.annotations;

import com.example.framework.common.validation.ConditionalNotNullValidValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author MaGuangZu
 * @since 2023-07-28
 */
@Target({TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = ConditionalNotNullValidValidator.class)
public @interface ConditionalNotNullValid {

	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
