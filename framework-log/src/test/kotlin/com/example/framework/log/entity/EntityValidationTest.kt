package com.example.framework.log.entity

import com.example.framework.log.entity.dto.LogRecordDto
import com.example.framework.testsupport.BaseCase
import org.assertj.core.api.Assertions.assertThat
import org.dromara.hutool.core.util.RandomUtil
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
		assertThat(validateResult).hasSize(4)

		validateResult = validate(Locale.US, record)
		validateResult(nullInvalidMessageEn, validateResult)
		assertThat(validateResult).hasSize(4)

		val longLengthString = RandomUtil.randomString(1000)
		assertThat(longLengthString.length > 511).isTrue()

		record.type = longLengthString
		record.bizNo = longLengthString
		record.operator = longLengthString
		record.action = longLengthString

		validateResult = validate(record)
		validateResult(lengthInvalidMessageZh, validateResult)
		assertThat(validateResult).hasSize(4)

		validateResult = validate(Locale.US, record)
		validateResult(lengthInvalidMessageEn, validateResult)
		assertThat(validateResult).hasSize(4)
	}

	@Test
	fun `test record dto validation`() {
		val recordDto = LogRecordDto(0, 10, Sort.unsorted())
		var validateResult = validate(recordDto)
		assertThat(validateResult).hasSize(0)

		val longLengthString = RandomUtil.randomString(1000)
		assertThat(longLengthString.length > 511).isTrue()

		recordDto.type = longLengthString
		recordDto.bizNo = longLengthString
		recordDto.operator = longLengthString
		recordDto.action = longLengthString

		validateResult = validate(recordDto)
		validateResult(lengthInvalidMessageZh, validateResult)
		assertThat(validateResult).hasSize(4)

		validateResult = validate(Locale.US, recordDto)
		validateResult(lengthInvalidMessageEn, validateResult)
		assertThat(validateResult).hasSize(4)
	}

}
