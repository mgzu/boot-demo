package com.example.demo.framework.interceptor;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.example.demo.framework.constants.WebConstants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * request id interceptor
 *
 * @author MaGuangZu
 * @since 2021-12-01
 */
@Slf4j
public class RequestIdInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestId = getRequestId(request, response);
        MDC.put(WebConstants.REQUEST_ID_KEY, requestId);
        response.setHeader(WebConstants.REQUEST_ID_KEY, requestId);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        MDC.remove(WebConstants.REQUEST_ID_KEY);
    }

    /**
     * 获取 request id
     *
     * @param request  请求
     * @param response 响应
     * @return request id
     */
    public static String getRequestId(HttpServletRequest request, HttpServletResponse response) {
        String requestId = request.getHeader(WebConstants.REQUEST_ID_KEY);
        if (StrUtil.isNotBlank(requestId)) {
            return requestId;
        }
        requestId = response.getHeader(WebConstants.REQUEST_ID_KEY);
        if (StrUtil.isNotBlank(requestId)) {
            return requestId;
        }
        requestId = request.getParameter(WebConstants.REQUEST_ID_KEY);
        if (StrUtil.isNotBlank(requestId)) {
            return requestId;
        }
        requestId = IdUtil.fastUUID();
        return requestId;
    }
}
