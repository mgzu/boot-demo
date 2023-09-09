package com.example.framework.system.repository;

import com.example.framework.web.aspects.TenantAspect;
import com.example.framework.web.contexts.TenantContext;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author MaGuangZu
 * @since 2023-09-06
 */
class DictItemRepositoryTenantEnableTest extends DictItemRepositoryBaseTest {

	@Test
	void testFindAll() {
		assertThat(dictItemRepository.findAll()).isEmpty();
		randomAndSave();
		assertThat(dictItemRepository.findAll()).hasSize(1);
		setRandomTenantId();
		randomAndSave();
		assertThat(dictItemRepository.findAll()).hasSize(1);
		TenantContext.setDisable(true);
		assertThat(dictItemRepository.findAll()).hasSize(2);
	}

	@Test
	void tenantAspectBeanShouldBeNotNull() {
		TenantAspect bean = applicationContext.getBean(TenantAspect.class);
		assertThat(bean).isNotNull();
	}

}
