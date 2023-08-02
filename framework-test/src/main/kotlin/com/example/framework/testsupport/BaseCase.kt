package com.example.framework.testsupport

import cn.hutool.core.io.resource.ResourceUtil
import cn.hutool.core.text.csv.CsvReader
import cn.hutool.core.text.csv.CsvUtil
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.validation.ConstraintViolation
import jakarta.validation.Validation
import jakarta.validation.Validator
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import org.hibernate.validator.spi.messageinterpolation.LocaleResolverContext
import org.junit.jupiter.api.Assertions
import java.nio.charset.StandardCharsets
import java.util.*
import java.util.Locale.LanguageRange
import java.util.function.Supplier

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
open class BaseCase {

	val objectMapper = ObjectMapper()

	private var acceptLanguage: Supplier<String>? = null

	private var validator: Validator = Validation.byDefaultProvider()
		.configure()
		.messageInterpolator(
			ParameterMessageInterpolator(
				mutableSetOf(
					Locale.CHINA,
					Locale.US
				),  // supported locales
				Locale.CHINA,  // default locale
				{ context: LocaleResolverContext ->
					if (Objects.nonNull(acceptLanguage)) {
						val acceptedLanguages = LanguageRange.parse(acceptLanguage!!.get())
						val resolvedLocales = Locale.filter(acceptedLanguages, context.supportedLocales)
						if (resolvedLocales.isNotEmpty()) {
							return@ParameterMessageInterpolator resolvedLocales[0]
						}
					}
					return@ParameterMessageInterpolator context.defaultLocale
				}, false
			)
		)
		.buildValidatorFactory()
		.validator

	fun <T> validate(obj: T): Set<ConstraintViolation<T>> {
		acceptLanguage = null
		return validator.validate(obj)
	}

	fun <T> validate(lang: Locale, obj: T): Set<ConstraintViolation<T>> {
		acceptLanguage = Supplier { lang.language }
		return validator.validate(obj)
	}

	fun <T> validateResult(collection: Collection<String>, validateResult: Set<ConstraintViolation<T>>) {
		validateResult.stream().forEach { Assertions.assertTrue(collection.contains(it.message)) }
	}

	fun <T> printResult(validateResult: Set<ConstraintViolation<T>>) {
		if (validateResult.isNotEmpty()) {
			validateResult.forEach { println(it) }
		}
	}

	var reader: CsvReader = CsvUtil.getReader()

	fun <T> readCsvList(path: String, clazz: Class<T>): List<T> {
		return reader.read(ResourceUtil.getReader(path, StandardCharsets.UTF_8), clazz)
	}
}
