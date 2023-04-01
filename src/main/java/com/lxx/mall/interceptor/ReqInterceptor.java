package com.lxx.mall.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 林修贤
 * @date 2023/4/1
 * @description
 */
public class ReqInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LogManager.getLogger(HandlerInterceptorAdapter.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = request.getRemoteAddr();
        logger.info("请求的 IP 地址为：{}", ip);
        return true;
    }
}
