package com.example.framework.web.validation;

import com.example.framework.web.annotations.ConstantsValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;
import java.util.Set;

/**
 * @author MaGuangZu
 * @since 2023-07-25
 */
public class ConstantsValidatorConstraint implements ConstraintValidator<ConstantsValidator, String> {
	Set<String> values;

	@Override
	public void initialize(ConstantsValidator validator) {
		values = Set.of(validator.constants());
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return Objects.nonNull(value) && values.contains(value);
	}

}
