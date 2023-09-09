package com.example.framework.system.repository;

import com.example.framework.web.contexts.TenantContext;
import org.dromara.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.BeforeEach;

public class TenantBaseCase {

	@BeforeEach
	void before() {
		setRandomTenantId();
	}

	void setRandomTenantId() {
		TenantContext.setTenantId(RandomUtil.randomString(10));
	}

}
