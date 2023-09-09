package com.example.framework.web.contexts;

import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class TenantContext {

	private static final ThreadLocal<Boolean> DISABLE = new ThreadLocal<>();

	private static final ThreadLocal<String> TENANT_ID = new ThreadLocal<>();

	public static void setDisable(Boolean disable) {
		DISABLE.set(disable);
	}

	public static Boolean getDisable() {
		return Optional.ofNullable(DISABLE.get()).orElse(Boolean.FALSE);
	}

	public static void setTenantId(String tenantId) {
		TENANT_ID.set(tenantId);
	}

	public static String getTenantId() {
		return TENANT_ID.get();
	}

	public static void clear() {
		DISABLE.remove();
		TENANT_ID.remove();
	}

}
