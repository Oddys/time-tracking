package org.oddys.timetracking.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/cabinet/*")
public class AuthFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getSession().getAttribute("user") == null) {
            LOGGER.info("Access to " + req.getServletPath() + " by an unauthorized user is denied");
            resp.sendRedirect(req.getContextPath());
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {}
}
