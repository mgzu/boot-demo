package com.example.framework.system.mapper;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.example.framework.system.entity.Dict;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author MaGuangZu
 * @since 2023-08-31
 */
@MybatisPlusTest
class DictMapperTest extends TenantBaseCase {

	@Autowired
	DictMapper dictMapper;

	@Test
	void testSelectList() {
		assertThat(dictMapper.selectList(null)).isEmpty();
	}

	@Test
	void testUniqueIndex() {
		String code = "unique";
		Dict dict = new Dict();
		dict.setCode(code);
		dict.setName("name");
		dictMapper.insert(dict);
		Dict dict2 = new Dict();
		dict2.setCode(code);
		dict2.setName("name");
		assertThatThrownBy(() -> dictMapper.insert(dict2))
			.isInstanceOf(DataIntegrityViolationException.class)
			.hasMessageContaining("unique");
	}

}
