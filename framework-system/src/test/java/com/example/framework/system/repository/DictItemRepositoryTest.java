package com.example.framework.system.repository;

import com.example.framework.system.constants.DictConstants;
import com.example.framework.system.entity.DictItem;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author MaGuangZu
 * @since 2023-09-06
 */
@DataJpaTest
class DictItemRepositoryTest {
	@Autowired
	DictItemRepository dictItemRepository;

	@Autowired
	EntityManager entityManager;

	@Test
	void testFindAll() {
		assertThat(dictItemRepository.findAll()).isEmpty();
	}

	@Test
	void testSaveByDefault() {
		DictItem dictItem = new DictItem();
		dictItem.setDictId("0");
		dictItem.setType(DictConstants.DICT_TYPE_BOOL);
		dictItem.setValue(Boolean.FALSE.toString());
		DictItem saved = dictItemRepository.saveAndFlush(dictItem);
		assertThat(saved.getVersionLock()).isZero();
		assertThat(saved.getOrderByPriority()).isNull();
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
		DictItem saved = dictItemRepository.saveAndFlush(dictItem);
		assertThat(saved.getId()).isNotBlank();
		assertThat(saved.getOrderByPriority()).isEqualTo(10);
	}

}
