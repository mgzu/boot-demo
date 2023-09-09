package com.example.framework.system.repository;

import com.example.framework.web.aspects.TenantAspect;
import com.example.framework.web.contexts.TenantContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author MaGuangZu
 * @since 2023-09-06
 */
@DataJpaTest(properties = "framework.tenant.enable=false")
class DictItemRepositoryTenantDisableTest extends DictItemRepositoryBaseTest {

	@Test
	void testFindAll() {
		assertThat(dictItemRepository.findAll()).isEmpty();
		randomAndSave();
		assertThat(dictItemRepository.findAll()).hasSize(1);
		setRandomTenantId();
		randomAndSave();
		assertThat(dictItemRepository.findAll()).hasSize(2);
		TenantContext.setDisable(false);
		assertThat(dictItemRepository.findAll()).hasSize(2);
	}

	@Test
	void tenantAspectBeanShouldBeNoSuch() {
		assertThatThrownBy(() -> applicationContext.getBean(TenantAspect.class))
			.isInstanceOf(NoSuchBeanDefinitionException.class);
	}

}
