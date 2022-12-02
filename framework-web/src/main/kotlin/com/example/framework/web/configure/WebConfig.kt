package com.example.framework.web.configure

import cn.hutool.core.util.StrUtil
import com.example.framework.web.constants.WebConstants
import com.example.framework.web.interceptor.RequestIdInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.*

/**
 * @author MaGuangZu
 * @since 2021-12-03
 */
@Configuration
open class WebConfig : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        val staticResourcePatterns =
            Arrays.stream(WebConstants.STATIC_RESOURCE_PATTERNS.split(StrUtil.COMMA).toTypedArray())
                .map { i -> "/*$i" }
                .toList()
        registry.addInterceptor(RequestIdInterceptor())
            .addPathPatterns("/**")
            .excludePathPatterns(staticResourcePatterns)
    }
}
