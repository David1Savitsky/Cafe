package com.epam.webappfinal.filter;


import javax.servlet.*;
import java.io.IOException;

public class CharacterSetFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
