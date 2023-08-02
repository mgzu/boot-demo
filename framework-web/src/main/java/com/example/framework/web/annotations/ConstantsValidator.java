package com.example.framework.web.annotations;

import com.example.framework.web.validation.ConstantsValidatorConstraint;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

/**
 * @author MaGuangZu
 * @since 2023-07-25
 */
@Documented
@Constraint(validatedBy = ConstantsValidatorConstraint.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@NotNull
public @interface ConstantsValidator {

	String[] constants();

	String message() default "must be any of constant {constants}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
