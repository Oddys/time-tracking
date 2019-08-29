package org.oddys.timetracking.controller.filter;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        urlPatterns = {"/*"}
)
public class I18nFilter implements Filter {
    private static final Logger log = LogManager.getLogger();

    private static final String LANG_STRING = "lang";
    private static final String DEFAULT_LANG = "en";

    public I18nFilter() {
        super();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        String langParam = servletRequest.getParameter(LANG_STRING);
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        if (langParam != null) {
            session.setAttribute(LANG_STRING, langParam);
            log.info("Session language is set to " + langParam);
//            ((HttpServletResponse) servletResponse).sendRedirect(((HttpServletRequest) servletRequest).getRequestURI()); // TODO Check if it's ok
        } else if (session.getAttribute(LANG_STRING) == null) {
            servletRequest.setAttribute(LANG_STRING, DEFAULT_LANG);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
