package com.julher625.deliveryControlSystem.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class HeaderLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        System.out.println("Headers:");
        httpRequest.getHeaderNames()
                .asIterator()
                .forEachRemaining(headerName ->
                        System.out.println(headerName + ": " + httpRequest.getHeader(headerName)));
        chain.doFilter(request, response);
    }
}
