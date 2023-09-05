package com.example.framework.web.configure.jpa;

import com.example.framework.web.constants.TenantConstants;
import com.example.framework.web.util.TenantContext;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.jetbrains.annotations.NotNull;

public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

	@NotNull
	@Override
	public String resolveCurrentTenantIdentifier() {
		return TenantContext.getTenant().get();
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return false;
	}

	@Override
	public boolean isRoot(@NotNull String tenantId) {
		return TenantConstants.ROOT_TENANT.equals(tenantId);
	}

}
