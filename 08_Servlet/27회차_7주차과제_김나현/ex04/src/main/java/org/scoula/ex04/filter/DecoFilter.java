package org.scoula.ex04.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class DecoFilter implements Filter{
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String url = req.getRequestURI();

        System.out.println(url + " ========>");

        chain.doFilter(request, response);

        System.out.println("<========");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }
}
