package com.example.framework.log.entity;

import com.example.framework.log.entity.dto.LogRecordDto;
import com.example.framework.testsupport.BaseCase;
import org.dromara.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author MaGuangZu
 * @since 2024-05-19
 */
class EntityValidationTest extends BaseCase {

	private final Set<String> nullInvalidMessageZh = Set.of("不能为空");
	private final Set<String> nullInvalidMessageEn = Set.of("must not be blank");
	private final Set<String> lengthInvalidMessageZh = Set.of(
		"长度需要在0和63之间",
		"长度需要在0和200之间",
		"长度需要在0和511之间"
	);
	private final Set<String> lengthInvalidMessageEn = Set.of(
		"length must be between 0 and 63",
		"length must be between 0 and 200",
		"length must be between 0 and 511"
	);

	@Test
	void testRecordValidation() {
		var record = new LogRecord();
		var validateResult = validate(record);
		validateResult(nullInvalidMessageZh, validateResult);
		assertThat(validateResult).hasSize(4);

		validateResult = validate(Locale.US, record);
		validateResult(nullInvalidMessageEn, validateResult);
		assertThat(validateResult).hasSize(4);

		var longLengthString = RandomUtil.randomString(1000);
		assertThat(longLengthString.length() > 511).isTrue();

		record.setType(longLengthString);
		record.setBizNo(longLengthString);
		record.setOperator(longLengthString);
		record.setAction(longLengthString);

		validateResult = validate(record);
		validateResult(lengthInvalidMessageZh, validateResult);
		assertThat(validateResult).hasSize(4);

		validateResult = validate(Locale.US, record);
		validateResult(lengthInvalidMessageEn, validateResult);
		assertThat(validateResult).hasSize(4);
	}

	@Test
	void testRecordDtoValidation() {
		var recordDto = new LogRecordDto();
		var validateResult = validate(recordDto);
		assertThat(validateResult).hasSize(0);

		var longLengthString = RandomUtil.randomString(1000);
		assertThat(longLengthString.length() > 511).isTrue();

		recordDto.setType(longLengthString);
		recordDto.setBizNo(longLengthString);
		recordDto.setOperator(longLengthString);
		recordDto.setAction(longLengthString);

		validateResult = validate(recordDto);
		validateResult(lengthInvalidMessageZh, validateResult);
		assertThat(validateResult).hasSize(4);

		validateResult = validate(Locale.US, recordDto);
		validateResult(lengthInvalidMessageEn, validateResult);
		assertThat(validateResult).hasSize(4);
	}

}
