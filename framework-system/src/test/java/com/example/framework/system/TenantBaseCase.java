package com.example.framework.system;

import com.example.framework.testsupport.BaseCase;
import com.example.framework.web.contexts.TenantContext;
import com.example.framework.web.entity.BaseEntity;
import org.dromara.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.BeforeEach;

public class TenantBaseCase extends BaseCase {

	@BeforeEach
	void before() {
		setRandomTenantId();
	}

	protected void setRandomTenantId() {
		TenantContext.setTenantId(RandomUtil.randomString(10));
	}

	protected void applyDefault(BaseEntity base) {
		base.setAppVersion("0.0.1");
		base.setDataVersion("0.0.1");
	}
}
