package com.example.framework.system.entity

import com.example.framework.system.constants.DictConstants
import com.example.framework.system.entity.vo.DictItemVo
import com.example.framework.system.entity.vo.DictVo
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
class DictItemTest : BaseCase() {

	@Test
	@Throws(JsonProcessingException::class)
	fun test() {
		val dicts: MutableList<DictItem> = ArrayList()
		dicts.add(of("string", "123"))
		dicts.add(of("int", "123"))
		dicts.add(of("bool", "true"))
		dicts.add(of("decimal", "1.333333333333333333333333"))
		val toList = DictUtil.convert(dicts)
		println(objectMapper.writeValueAsString(toList))
	}

	private fun of(type: String?, value: String?): DictItem {
		val dictItem = DictItem()
		dictItem.type = type
		dictItem.value = value
		dictItem.createdBy = "createdBy"
		dictItem.createdDate = LocalDateTime.now()
		dictItem.lastModifiedBy = "lastModifiedBy"
		dictItem.lastModifiedDate = LocalDateTime.now()
		dictItem.versionLock = 0
		return dictItem
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
