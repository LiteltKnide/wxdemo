package com.xiaodao.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by hujt49 on 2018/05/31.
 */
@Order(1)
@WebFilter(urlPatterns = "/*")
// @Component
public class MainFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(MainFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        Map<String, String[]> parameterMap = request.getParameterMap();
        logger.info("============params start=============");
        parameterMap.forEach((k, v) -> {
            logger.info(k + ":" + v);
        });
        logger.info("============params end=============");

        request.setCharacterEncoding("utf-8");
        response.setContentType("Application/json");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
