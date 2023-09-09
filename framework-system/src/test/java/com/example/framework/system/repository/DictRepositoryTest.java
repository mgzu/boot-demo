package com.example.framework.system.repository;

import com.example.framework.system.entity.Dict;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author MaGuangZu
 * @since 2023-08-31
 */
@DataJpaTest
class DictRepositoryTest extends TenantBaseCase {

	@Autowired
	DictRepository dictRepository;

	@Autowired
	EntityManager entityManager;

	@Test
	void testFindAll() {
		assertThat(dictRepository.findAll()).isEmpty();
	}

	@Test
	void testUniqueIndex() {
		String code = "unique";
		Dict dict = new Dict();
		dict.setCode(code);
		dict.setName("name");
		dictRepository.saveAndFlush(dict);
		assertThatThrownBy(() -> dictRepository.saveAndFlush(dict))
			.isInstanceOf(DataIntegrityViolationException.class).hasMessageContaining("unique");
	}

}
