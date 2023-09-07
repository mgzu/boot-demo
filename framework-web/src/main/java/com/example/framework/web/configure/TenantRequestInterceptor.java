package com.example.framework.web.configure;

import com.example.framework.web.constants.TenantConstants;
import com.example.framework.web.contexts.TenantContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import java.util.Optional;

@Component
public class TenantRequestInterceptor implements AsyncHandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		return Optional.ofNullable(request)
//			.map(req -> securityDomain.getTenantIdFromJwt(req))
			.map(tenant -> setTenantContext(TenantConstants.ROOT_TENANT))
			.orElse(false);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		TenantContext.clear();
	}

	private boolean setTenantContext(String tenant) {
		TenantContext.setTenantId(tenant);
		return true;
	}

}
