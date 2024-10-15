package com.example.lab_2jt;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ResponseDecoratorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Create a wrapper for the response to capture the output
        ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) response);

        // Continue with the next filter or target resource
        chain.doFilter(request, responseWrapper);

        // Get prelude and coda from application scope
        String prelude = (String) request.getServletContext().getAttribute("prelude");
        String coda = (String) request.getServletContext().getAttribute("coda");

        // Write the prelude and coda
        PrintWriter out = response.getWriter();
        out.write(prelude);
        out.write(responseWrapper.getOutput());
        out.write(coda);
    }

    @Override
    public void destroy() {
    }

    private static class ResponseWrapper extends HttpServletResponseWrapper {
        private StringWriter output = new StringWriter();
        private PrintWriter writer = new PrintWriter(output);

        public ResponseWrapper(HttpServletResponse response) {
            super(response);
        }

        @Override
        public PrintWriter getWriter() {
            return writer;
        }

        public String getOutput() {
            writer.flush();
            return output.toString();
        }
    }
}
