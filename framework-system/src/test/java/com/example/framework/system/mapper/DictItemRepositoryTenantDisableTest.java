package com.example.framework.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.example.framework.web.contexts.TenantContext;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author MaGuangZu
 * @since 2023-09-06
 */
@MybatisPlusTest(properties = "framework.tenant.enable=false")
class DictItemRepositoryTenantDisableTest extends DictItemRepositoryBaseTest {

	@Test
	void doesNotHaveTenantLineInnerInterceptor() {
		MybatisPlusInterceptor mybatisPlusInterceptor = applicationContext.getBean(MybatisPlusInterceptor.class);
		assertThat(mybatisPlusInterceptor.getInterceptors())
			.doesNotHaveAnyElementsOfTypes(TenantLineInnerInterceptor.class);
	}

	@Test
	void testSelectList() {
		assertThat(dictItemMapper.selectList(null)).isEmpty();
		randomAndSave();
		assertThat(dictItemMapper.selectList(null)).hasSize(1);
		setRandomTenantId();
		randomAndSave();
		assertThat(dictItemMapper.selectList(null)).hasSize(2);
		TenantContext.setDisable(false);
		assertThat(dictItemMapper.selectList(null)).hasSize(2);
	}

}
