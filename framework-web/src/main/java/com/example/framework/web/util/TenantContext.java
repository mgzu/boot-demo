package com.example.framework.web.util;

import org.springframework.stereotype.Component;

@Component
public class TenantContext {

	private static ThreadLocal<String> tenant = new ThreadLocal<>();

	public static ThreadLocal<String> getTenant() {
		return tenant;
	}

}
