package com.example.framework.web.interceptor

import com.example.framework.web.constants.TenantConstants
import com.example.framework.web.contexts.TenantContext
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.lang.Nullable
import org.springframework.web.servlet.AsyncHandlerInterceptor
import java.util.*

class TenantRequestInterceptor : AsyncHandlerInterceptor {

	override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
		return Optional.ofNullable(request)
			// .map(req -> securityDomain.getTenantIdFromJwt(req))
			.map { _: HttpServletRequest? -> setTenantContext(TenantConstants.ROOT_TENANT) }
			.orElse(false)
	}

	@Throws(Exception::class)
	override fun afterCompletion(
		request: HttpServletRequest,
		response: HttpServletResponse,
		handler: Any,
		@Nullable ex: java.lang.Exception?
	) {
		TenantContext.clear()
	}

	private fun setTenantContext(tenant: String): Boolean {
		TenantContext.setTenantId(tenant)
		return true
	}

}
