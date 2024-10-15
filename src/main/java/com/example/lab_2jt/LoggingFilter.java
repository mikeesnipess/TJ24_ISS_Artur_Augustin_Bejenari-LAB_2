package com.example.lab_2jt;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;

public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Log the request details
        System.out.println("Received request for: " + request.getServletContext().getContextPath() + "/input.jsp");

        // Continue with the next filter or target resource
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
