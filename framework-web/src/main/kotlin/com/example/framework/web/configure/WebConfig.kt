package com.example.framework.web.configure

import com.example.framework.web.constants.WebConstants
import com.example.framework.web.interceptor.RequestIdInterceptor
import org.dromara.hutool.core.text.StrUtil
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * @author MaGuangZu
 * @since 2021-12-03
 */
@Configuration
open class WebConfig : WebMvcConfigurer {

	override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(RequestIdInterceptor())
            .addPathPatterns("/**")
			.excludePathPatterns(WebConstants.STATIC_RESOURCE_PATTERNS.split(StrUtil.COMMA)
				.map { "/*$it" }
				.toList())
    }

}
