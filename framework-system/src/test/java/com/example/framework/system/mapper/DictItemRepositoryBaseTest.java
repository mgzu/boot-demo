package com.example.framework.system.mapper;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.example.framework.system.constants.DictConstants;
import com.example.framework.system.entity.DictItem;
import org.dromara.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author MaGuangZu
 * @since 2023-09-06
 */
@MybatisPlusTest
class DictItemRepositoryBaseTest extends TenantBaseCase {
	@Autowired
	protected DictItemMapper dictItemMapper;

	@Autowired
	ApplicationContext applicationContext;

	protected void randomAndSave() {
		DictItem dictItem = new DictItem();
		dictItem.setDictId(RandomUtil.randomString(10));
		dictItem.setType(DictConstants.DICT_TYPE_BOOL);
		dictItem.setValue(Boolean.toString(RandomUtil.randomBoolean()));
		dictItemMapper.insert(dictItem);
	}

	@Test
	void testSaveByDefault() {
		DictItem dictItem = new DictItem();
		dictItem.setDictId("0");
		dictItem.setType(DictConstants.DICT_TYPE_BOOL);
		dictItem.setValue(Boolean.FALSE.toString());
		dictItemMapper.insert(dictItem);
		assertThat(dictItem.getId()).isNotBlank();
		DictItem saved = dictItemMapper.selectById(dictItem.getId());
		assertThat(saved.getOrderByPriority()).isZero();
	}

	@Test
	void testSave() {
		DictItem dictItem = new DictItem();
		dictItem.setDictId("0");
		dictItem.setType(DictConstants.DICT_TYPE_BOOL);
		dictItem.setValue(Boolean.FALSE.toString());
		dictItem.setOrderByPriority(10);
		dictItemMapper.insert(dictItem);
		assertThat(dictItem.getId()).isNotBlank();
		DictItem saved = dictItemMapper.selectById(dictItem.getId());
		assertThat(saved.getOrderByPriority()).isEqualTo(10);
	}

}
