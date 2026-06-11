package org.scoula.ex04.filter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class PerformanceMonitorFilter implements Filter{
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        long startTime = System.currentTimeMillis();
        String url = req.getRequestURI();

        chain.doFilter(request, response);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("[" + startTime + "] " + url + " - " + duration + "ms 소요.");
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
