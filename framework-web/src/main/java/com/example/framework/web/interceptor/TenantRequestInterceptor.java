package com.example.framework.web.interceptor;

import com.example.framework.web.constants.TenantConstants;
import com.example.framework.web.contexts.TenantContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import java.util.Optional;

/**
 * @author MaGuangZu
 * @since 2024-05-19
 */
public class TenantRequestInterceptor implements AsyncHandlerInterceptor {

	@Override
	public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
		return Optional.of(request)
			// .map(req -> securityDomain.getTenantIdFromJwt(req))
			.map(it -> {
				return setTenantContext(TenantConstants.ROOT_TENANT);
			})
			.orElse(false);
	}

	@Override
	public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception ex) throws Exception {
		TenantContext.clear();
	}

	private Boolean setTenantContext(String tenant) {
		TenantContext.setTenantId(tenant);
		return true;
	}

}
