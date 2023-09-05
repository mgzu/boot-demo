package com.example.framework.system.repository;

import com.example.framework.system.constants.DictConstants;
import com.example.framework.system.entity.Dict;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author MaGuangZu
 * @since 2023-08-31
 */
@DataJpaTest
class DictRepositoryTest {

	@Autowired
	DictRepository dictRepository;

	@Autowired
	EntityManager entityManager;

	@Test
	void testSaveByDefault() {
		Dict dict = new Dict();
		dict.setDictType(DictConstants.DICT_TYPE_BOOL);
		dict.setValue(Boolean.FALSE.toString());
		Dict saved = dictRepository.saveAndFlush(dict);
		assertThat(saved.getOrderPriority()).isNull();
		// force refresh, get data from database
		entityManager.refresh(saved);
		assertThat(saved.getId()).isNotBlank();
		assertThat(saved.getOrderPriority()).isZero();
		assertThat(saved.getVersionLock()).isZero();
	}

	@Test
	void testSave() {
		Dict dict = new Dict();
		dict.setDictType(DictConstants.DICT_TYPE_BOOL);
		dict.setValue(Boolean.FALSE.toString());
		dict.setOrderPriority(10);
		Dict saved = dictRepository.saveAndFlush(dict);
		assertThat(saved.getId()).isNotBlank();
		assertThat(saved.getOrderPriority()).isEqualTo(10);
		assertThat(saved.getVersionLock()).isZero();
	}

	@Test
	void testFindAll() {
		assertThat(dictRepository.findAll()).isEmpty();
	}

}
