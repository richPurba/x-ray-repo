package main.com.xray.filters;

import main.com.xray.services.StatisticsService;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// the wild pattern below specifies interception of every request
@WebFilter("/*")
public class StatisticsFilter implements Filter {
    @EJB
    StatisticsService service;
    public static String REFERRER = "referrer";

    @Override
    public void init(FilterConfig filterConfig){}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String uri = httpServletRequest.getRequestURI();
        String referrer = httpServletRequest.getRequestURI();
        chain.doFilter(request,response);
        service.store(uri,referrer);
    }

    @Override
    public void destroy(){}
}
