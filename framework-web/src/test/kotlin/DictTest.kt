package com.example.framework.web.entity

import com.example.framework.testsupport.BaseCase
import com.fasterxml.jackson.core.JsonProcessingException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

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
		dicts.forEach { println(it) }
		println(objectMapper.writeValueAsString(dicts))
	}

	private fun addDict(type: String?, value: String?, dicts: MutableList<Dict>) {
		val dict = Dict()
		dict.dictType = type
		dict.value = value
		dicts.add(dict)
	}

	private val invalidMessageEn = setOf("must be any of constant [string, int, decimal, bool]")

	@Test
	fun testValid() {
		val dict = Dict()
		dict.dictType = "unknown"
		dict.value = "unknown"
		var result = validate(dict)
		Assertions.assertEquals(1, result.size)
		validateResult(invalidMessageEn, result)
		dict.dictType = "string"
		result = validate(dict)
		Assertions.assertEquals(0, result.size)
	}
}
