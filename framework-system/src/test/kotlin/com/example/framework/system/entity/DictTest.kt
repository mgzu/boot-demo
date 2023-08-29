package com.example.framework.system.entity

import com.example.framework.system.constants.DictConstants
import com.example.framework.system.util.DictUtil
import com.example.framework.testsupport.BaseCase
import com.fasterxml.jackson.core.JsonProcessingException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.NullAndEmptySource
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
		val toList = DictUtil.typeConvert(dicts)
		println(objectMapper.writeValueAsString(toList))
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
		"不能为空"
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
	fun `test invalid`(type: String) {
		val dict = Dict()
		dict.dictType = type
		dict.value = "unknown"
		val result = validate(dict)
		assertThat(result).hasSize(1)
		validateResult(invalidMessage, result)
	}

	@NullAndEmptySource
	@ParameterizedTest
	fun `test invalid by null or empty string`(type: String?) {
		val dict = Dict()
		dict.dictType = type
		dict.value = type
		val result = validate(dict)
		assertThat(result).hasSizeBetween(2, 3)
		printResult(result)
		validateResult(invalidMessage, result)
	}

}
