package com.example.framework.web.interceptor;

import com.example.framework.web.constants.WebConstants;
import io.micrometer.tracing.TraceContext;
import io.micrometer.tracing.Tracer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author MaGuangZu
 * @since 2024-05-19
 */
@AllArgsConstructor
public class TraceIdInterceptor implements HandlerInterceptor {

	private final Tracer tracer;

	@Override
	public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
		TraceContext context = tracer.currentTraceContext().context();
		if (context != null && context.traceId() != null) {
			response.setHeader(WebConstants.TRACE_ID_KEY, context.traceId());
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
