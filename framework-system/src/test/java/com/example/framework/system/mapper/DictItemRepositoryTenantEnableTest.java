package com.example.framework.system.mapper;

import com.example.framework.web.contexts.TenantContext;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author MaGuangZu
 * @since 2023-09-06
 */
class DictItemRepositoryTenantEnableTest extends DictItemRepositoryBaseTest {
//	@Test
//	void tenantAspectBeanShouldBeNotNull() {
//		TenantAspect bean = applicationContext.getBean(TenantAspect.class);
//		assertThat(bean).isNotNull();
//	}

	@Test
	void testSelectList() {
		assertThat(dictItemMapper.selectList(null)).isEmpty();
		randomAndSave();
		assertThat(dictItemMapper.selectList(null)).hasSize(1);
		setRandomTenantId();
		randomAndSave();
		assertThat(dictItemMapper.selectList(null)).hasSize(1);
		TenantContext.setDisable(true);
		assertThat(dictItemMapper.selectList(null)).hasSize(2);
	}

}
