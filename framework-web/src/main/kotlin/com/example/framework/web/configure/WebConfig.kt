package com.example.framework.web.configure

import com.example.framework.web.constants.WebConstants
import com.example.framework.web.interceptor.TenantRequestInterceptor
import com.example.framework.web.interceptor.TraceIdInterceptor
import io.micrometer.tracing.Tracer
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * @author MaGuangZu
 * @since 2021-12-03
 */
@Configuration
open class WebConfig(
	private val tracer: Tracer,
) : WebMvcConfigurer {

	override fun addInterceptors(registry: InterceptorRegistry) {
		registry.addInterceptor(TraceIdInterceptor(tracer))
			.addPathPatterns("/**")
			.excludePathPatterns(WebConstants.STATIC_RESOURCE_PATTERNS)
		registry.addInterceptor(TenantRequestInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns(WebConstants.STATIC_RESOURCE_PATTERNS)
	}

}
