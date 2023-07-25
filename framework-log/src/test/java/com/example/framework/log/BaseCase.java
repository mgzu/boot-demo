package com.example.framework.log;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
public class BaseCase {

	Supplier<String> acceptLanguage = null;

	Validator validator = Validation.byDefaultProvider()
		.configure()
		.messageInterpolator(new ParameterMessageInterpolator(Set.of(Locale.CHINA, Locale.US), // supported locales
			Locale.CHINA, // default locale
			context -> {
				if (Objects.nonNull(acceptLanguage)) {
					var acceptedLanguages = Locale.LanguageRange.parse(acceptLanguage.get());
					var resolvedLocales = Locale.filter(acceptedLanguages, context.getSupportedLocales());
					if (resolvedLocales.size() > 0) {
						return resolvedLocales.get(0);
					}
				}
				return context.getDefaultLocale();
			}, false))
		.buildValidatorFactory()
		.getValidator();

	public Validator getValidator() {
		return validator;
	}

	public <T> Set<ConstraintViolation<T>> validate(T object) {
		acceptLanguage = null;
		return validator.validate(object);
	}

	public <T> Set<ConstraintViolation<T>> validate(Locale lang, T object) {
		acceptLanguage = lang::getLanguage;
		return validator.validate(object);
	}
}
