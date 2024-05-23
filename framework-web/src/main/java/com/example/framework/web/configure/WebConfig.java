package com.example.framework.web.configure;

import com.example.framework.web.constants.WebConstants;
import com.example.framework.web.interceptor.TenantRequestInterceptor;
import com.example.framework.web.interceptor.TraceIdInterceptor;
import io.micrometer.tracing.Tracer;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author MaGuangZu
 * @since 2021-12-03
 */
@AllArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
	public final Tracer tracer;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TraceIdInterceptor(tracer))
			.addPathPatterns("/**")
			.excludePathPatterns(WebConstants.STATIC_RESOURCE_PATTERNS);
		registry.addInterceptor(new TenantRequestInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns(WebConstants.STATIC_RESOURCE_PATTERNS);
	}

}
