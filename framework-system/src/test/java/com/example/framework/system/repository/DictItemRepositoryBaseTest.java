package com.example.framework.system.repository;

import com.example.framework.system.constants.DictConstants;
import com.example.framework.system.entity.DictItem;
import jakarta.persistence.EntityManager;
import org.dromara.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author MaGuangZu
 * @since 2023-09-06
 */
@DataJpaTest
class DictItemRepositoryBaseTest extends TenantBaseCase {
	@Autowired
	protected DictItemRepository dictItemRepository;

	@Autowired
	protected EntityManager entityManager;

	@Autowired
	ApplicationContext applicationContext;

	protected void randomAndSave() {
		DictItem dictItem = new DictItem();
		dictItem.setDictId(RandomUtil.randomString(10));
		dictItem.setType(DictConstants.DICT_TYPE_BOOL);
		dictItem.setValue(Boolean.toString(RandomUtil.randomBoolean()));
		dictItemRepository.saveAndFlush(dictItem);
	}

	@Test
	void testSaveByDefault() {
		DictItem dictItem = new DictItem();
		dictItem.setDictId("0");
		dictItem.setType(DictConstants.DICT_TYPE_BOOL);
		dictItem.setValue(Boolean.FALSE.toString());
		DictItem saved = dictItemRepository.save(dictItem);
		assertThat(saved.getVersionLock()).isZero();
		assertThat(saved.getOrderByPriority()).isNull();
		// need flush
		dictItemRepository.flush();
		// force refresh, get data from database
		entityManager.refresh(saved);
		assertThat(saved.getId()).isNotBlank();
		assertThat(saved.getOrderByPriority()).isZero();
	}

	@Test
	void testSave() {
		DictItem dictItem = new DictItem();
		dictItem.setDictId("0");
		dictItem.setType(DictConstants.DICT_TYPE_BOOL);
		dictItem.setValue(Boolean.FALSE.toString());
		dictItem.setOrderByPriority(10);
		DictItem saved = dictItemRepository.save(dictItem);
		assertThat(saved.getId()).isNotBlank();
		assertThat(saved.getOrderByPriority()).isEqualTo(10);
	}

}
