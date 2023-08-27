package com.example.framework.web.entity

import com.example.framework.testsupport.BaseCase
import com.example.framework.web.constants.DictConstants
import com.fasterxml.jackson.core.JsonProcessingException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.NullSource
import org.junit.jupiter.params.provider.ValueSource

/**
 * @author MaGuangZu
 * @since 2021-09-27
 */
class DictTest : BaseCase() {

	@Test
	@Throws(JsonProcessingException::class)
	fun test() {
		val dicts: MutableList<Dict> = ArrayList()
		addDict("string", "123", dicts)
		addDict("int", "123", dicts)
		addDict("bool", "true", dicts)
		addDict("decimal", "1.333333333333333333333333", dicts)
		dicts.forEach {
			when (it.dictType) {
				"int" -> it.value = it.value.toString().toInt()
				"decimal" -> it.value = it.value.toString().toBigDecimal()
				"bool" -> it.value = it.value.toString().toBoolean()
				else -> {}
			}
		}
		println(objectMapper.writeValueAsString(dicts))
	}

	private fun addDict(type: String?, value: String?, dicts: MutableList<Dict>) {
		val dict = Dict()
		dict.dictType = type
		dict.value = value
		dicts.add(dict)
	}

	private val invalidMessage = setOf(
		"must be any of constant [string, int, decimal, bool]",
		"不能为null",
	)

	@ValueSource(
		strings = [
			DictConstants.DICT_TYPE_STRING,
			DictConstants.DICT_TYPE_INT,
			DictConstants.DICT_TYPE_DECIMAL,
			DictConstants.DICT_TYPE_BOOL,
		]
	)
	@ParameterizedTest
	fun `test valid`(type: String) {
		val dict = Dict()
		dict.dictType = type
		dict.value = "unknown"
		val result = validate(dict)
		assertThat(result).hasSize(0)
	}

	@ValueSource(strings = ["unknown"])
	@ParameterizedTest
	fun `test in valid`(type: String) {
		val dict = Dict()
		dict.dictType = type
		dict.value = "unknown"
		val result = validate(dict)
		assertThat(result).hasSize(1)
		validateResult(invalidMessage, result)
	}

	@NullSource
	@ParameterizedTest
	fun `test in valid by null`(type: String?) {
		val dict = Dict()
		dict.dictType = type
		dict.value = "unknown"
		val result = validate(dict)
		assertThat(result).hasSize(2)
		validateResult(invalidMessage, result)
	}

}
