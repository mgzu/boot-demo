package com.example.framework.web.interceptor

import com.example.framework.web.constants.WebConstants.TRACE_ID_KEY
import io.micrometer.tracing.Tracer
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.extern.slf4j.Slf4j
import org.springframework.web.servlet.HandlerInterceptor

/**
 * trace id interceptor
 *
 * @author MaGuangZu
 * @since 2021-12-01
 */
@Slf4j
class TraceIdInterceptor(private val tracer: Tracer) : HandlerInterceptor {

	@Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
		response.setHeader(TRACE_ID_KEY, tracer.currentTraceContext().context()?.traceId())
        return super.preHandle(request, response, handler)
    }

}
