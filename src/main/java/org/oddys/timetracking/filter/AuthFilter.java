package org.oddys.timetracking.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.dto.UserDto;

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
import java.util.Set;

@WebFilter("/cabinet/*")
public class AuthFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Set<String> ADMIN_PAGES = Set.of(
            "user-data",
            "show-activity-requests"
    );

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        UserDto user = (UserDto) req.getSession().getAttribute("user");
        if (user == null) {
            LOGGER.info("Access to " + req.getServletPath() + " by an unauthorized user is denied");
            resp.sendRedirect(req.getContextPath());
        } else if (!"ADMIN".equals(user.getRoleName())
                && inAdminPages(req.getServletPath())) {
            resp.sendRedirect("/time-tracking/cabinet");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {}

    private String getEndpoint(String path) {
        return path.replaceFirst("/cabinet/", "");
    }

    private boolean inAdminPages(String path) {
        return ADMIN_PAGES.contains(getEndpoint(path));
    }
}
