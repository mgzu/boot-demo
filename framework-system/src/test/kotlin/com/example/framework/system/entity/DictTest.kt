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
import java.time.LocalDateTime

/**
 * @author MaGuangZu
 * @since 2021-09-27
 */
class DictTest : BaseCase() {

	@Test
	@Throws(JsonProcessingException::class)
	fun test() {
		val dicts: MutableList<Dict> = ArrayList()
		dicts.add(of("string", "123"))
		dicts.add(of("int", "123"))
		dicts.add(of("bool", "true"))
		dicts.add(of("decimal", "1.333333333333333333333333"))
		val toList = DictUtil.typeConvert(dicts)
		println(objectMapper.writeValueAsString(toList))
	}

	private fun of(type: String?, value: String?): Dict {
		val dict = Dict()
		dict.dictType = type
		dict.value = value
		dict.createdBy = "createdBy"
		dict.createdDate = LocalDateTime.now()
		dict.lastModifiedBy = "lastModifiedBy"
		dict.lastModifiedDate = LocalDateTime.now()
		dict.versionLock = 0
		return dict
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
		val dict = of(type, "unknown")
		val result = validate(dict)
		assertThat(result).hasSize(0)
	}

	@ValueSource(strings = ["unknown"])
	@ParameterizedTest
	fun `test invalid`(type: String) {
		val dict = of(type, "unknown")
		val result = validate(dict)
		assertThat(result).hasSize(1)
		validateResult(invalidMessage, result)
	}

	@NullAndEmptySource
	@ParameterizedTest
	fun `test invalid by null or empty string`(type: String?) {
		val dict = of(type, "unknown")
		val result = validate(dict)
		assertThat(result).hasSizeBetween(1, 2)
		printResult(result)
		validateResult(invalidMessage, result)
	}

}
