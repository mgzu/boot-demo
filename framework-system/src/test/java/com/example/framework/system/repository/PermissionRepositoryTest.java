package com.example.framework.system.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author MaGuangZu
 * @since 2023-08-31
 */
@DataJpaTest
class PermissionRepositoryTest extends TenantBaseCase {

	@Autowired
	PermissionRepository permissionRepository;

	@Test
	void testFindAll() {
		assertThat(permissionRepository.findAll()).isEmpty();
	}

}
