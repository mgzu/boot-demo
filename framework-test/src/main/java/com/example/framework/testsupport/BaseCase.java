package com.example.framework.testsupport;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.Collection;
import java.util.Locale;
import java.util.Set;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author MaGuangZu
 * @since 2024-05-15
 */
@Slf4j
public class BaseCase {

	protected ObjectMapper objectMapper = new ObjectMapper();

	private Supplier<String> acceptLanguage = null;

	private final Validator validator = Validation.byDefaultProvider()
		.configure()
		.messageInterpolator(
			new ParameterMessageInterpolator(
				Set.of(
					Locale.CHINA,
					Locale.US
				),  // supported locales
				Locale.CHINA,  // default locale
				context -> {
					if (acceptLanguage != null) {
						var acceptedLanguages = Locale.LanguageRange.parse(acceptLanguage.get());
						var resolvedLocales = Locale.filter(acceptedLanguages, context.getSupportedLocales());
						if (!resolvedLocales.isEmpty()) {
							return resolvedLocales.getFirst();
						}
					}
					return context.getDefaultLocale();
				},
				false
			)
		)
		.buildValidatorFactory()
		.getValidator();

	protected <T> Set<ConstraintViolation<T>> validate(T obj) {
		acceptLanguage = null;
		return validator.validate(obj);
	}

	<T> Set<ConstraintViolation<T>> validate(Locale lang, T obj) {
		acceptLanguage = lang::getLanguage;
		return validator.validate(obj);
	}

	protected <T> void validateResult(Collection<String> collection, Set<ConstraintViolation<T>> validateResult) {
		validateResult.forEach(it -> assertThat(collection).contains(it.getMessage()));
	}

	protected <T> void printResult(Set<ConstraintViolation<T>> validateResult) {
		if (!validateResult.isEmpty()) {
			validateResult.forEach(System.out::println);
		}
	}
}
