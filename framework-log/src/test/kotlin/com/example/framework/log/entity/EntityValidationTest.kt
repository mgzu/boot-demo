package com.example.framework.log.entity

import cn.hutool.core.util.RandomUtil
import com.example.framework.log.BaseCase
import com.example.framework.log.entity.dto.LogRecordDto
import jakarta.validation.ConstraintViolation
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.data.domain.Sort
import java.util.*

/**
 * @author MaGuangZu
 * @since 2023-07-24
 */
class EntityValidationTest : BaseCase() {

	private val nullInvalidMessageZh = setOf("不能为空")
	private val nullInvalidMessageEn = setOf("must not be blank")
	private val lengthInvalidMessageZh = setOf(
		"长度需要在0和63之间",
		"长度需要在0和200之间",
		"长度需要在0和511之间",
	)
	private val lengthInvalidMessageEn = setOf(
		"length must be between 0 and 63",
		"length must be between 0 and 200",
		"length must be between 0 and 511",
	)

	@Test
	fun `test record validation`() {
		val record = LogRecord()
		var validateResult = validate(record)
		validateResult(nullInvalidMessageZh, validateResult)
		Assertions.assertEquals(4, validateResult.size)

		validateResult = validate(Locale.US, record)
		validateResult(nullInvalidMessageEn, validateResult)
		Assertions.assertEquals(4, validateResult.size)

		val longLengthString = RandomUtil.randomString(1000)
		Assertions.assertTrue(longLengthString.length > 511)

		record.type = longLengthString
		record.bizNo = longLengthString
		record.operator = longLengthString
		record.action = longLengthString

		validateResult = validate(record)
		validateResult(lengthInvalidMessageZh, validateResult)
		Assertions.assertEquals(4, validateResult.size)

		validateResult = validate(Locale.US, record)
		validateResult(lengthInvalidMessageEn, validateResult)
		Assertions.assertEquals(4, validateResult.size)
	}

	private fun <T> validateResult(collection: Collection<String>, validateResult: Set<ConstraintViolation<T>>) {
		validateResult.stream().map { Assertions.assertTrue(collection.contains(it.message)) }
	}

	private fun <T> printResult(validateResult: Set<ConstraintViolation<T>>) {
		if (validateResult.isNotEmpty()) {
			validateResult.forEach { println(it) }
		}
	}

	@Test
	fun `test record dto validation`() {
		val recordDto = LogRecordDto(0, 10, Sort.unsorted())
		var validateResult = validate(recordDto)
		Assertions.assertEquals(0, validateResult.size)

		val longLengthString = RandomUtil.randomString(1000)
		Assertions.assertTrue(longLengthString.length > 511)

		recordDto.type = longLengthString
		recordDto.bizNo = longLengthString
		recordDto.operator = longLengthString
		recordDto.action = longLengthString

		validateResult = validate(recordDto)
		validateResult(lengthInvalidMessageZh, validateResult)
		Assertions.assertEquals(4, validateResult.size)

		validateResult = validate(Locale.US, recordDto)
		validateResult(lengthInvalidMessageEn, validateResult)
		Assertions.assertEquals(4, validateResult.size)
	}

}
