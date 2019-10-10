package org.oddys.timetracking.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Filter implementation that set ServletRequest encoding
 *
 * @see javax.servlet.Filter
 */
@WebFilter(urlPatterns = {"/*"})
public class EncodingFilter implements Filter {
    /**
     * @see Filter#init(FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    /**
     * Sets a desired encoding for this request.
     *
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        filterChain.doFilter(request, response);
    }

    /**
     * @see Filter#destroy()
     */
    @Override
    public void destroy() {}
}
