package com.example.framework.testsupport

import com.fasterxml.jackson.databind.MappingIterator
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvParser
import jakarta.validation.ConstraintViolation
import jakarta.validation.Validation
import jakarta.validation.Validator
import org.assertj.core.api.Assertions.assertThat
import org.dromara.hutool.core.io.resource.ResourceUtil
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import org.hibernate.validator.spi.messageinterpolation.LocaleResolverContext
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
		validateResult.stream().forEach { assertThat(collection).contains(it.message) }
	}

	fun <T> printResult(validateResult: Set<ConstraintViolation<T>>) {
		if (validateResult.isNotEmpty()) {
			validateResult.forEach { println(it) }
		}
	}

	private val csvMapper: CsvMapper = CsvMapper.builder()
		.enable(CsvParser.Feature.SKIP_EMPTY_LINES)
		.build()

	fun <T> readCsvList(path: String, clazz: Class<T>): List<T> {
		val schema = csvMapper.typedSchemaFor(clazz)
			.withHeader()
			.withColumnReordering(true)

		val iterator: MappingIterator<T> = csvMapper
			.readerWithTypedSchemaFor(clazz)
			.with(schema)
			.readValues(ResourceUtil.getReader(path, StandardCharsets.UTF_8))
		return iterator.readAll()
	}

}
