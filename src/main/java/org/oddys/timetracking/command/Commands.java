package org.oddys.timetracking.command;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.dto.UserDto;
import org.oddys.timetracking.service.LoginService;
import org.oddys.timetracking.util.ConfigProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public enum Commands implements Command {
    LOGIN {
        @Override
        public String execute(HttpServletRequest req) {
            String page = LOGIN_CHECK.execute(req);
            if (page != null) {
                return page;
            }
            UserDto user = LoginService.getInstance().logIn(req.getParameter("login"),
                    req.getParameter("password").toCharArray());
            if (user != null){
                req.getSession().setAttribute("user", user);
                log.info("User " + user.getLogin() + " logged in");
                page = ConfigProvider.getInstance().getProperty("path.cabinet");
            } else {
                log.info(req.getParameter("login") + " failed to log in");
                req.setAttribute("errorMessageKey", "auth.error.notfound");
                page = ConfigProvider.getInstance().getProperty("path.home");
            }
            return page;
        }
    },

    LOGIN_CHECK {
        @Override
        public String execute(HttpServletRequest req) {
            String errorMessageKey = null;
            if (StringUtils.isEmpty(req.getParameter("login"))) {
                errorMessageKey = "auth.error.nologin";
            } else if (StringUtils.isEmpty(req.getParameter("password"))) {
                errorMessageKey = "auth.error.nopassword";
            }
            String page = null;
            if (errorMessageKey != null) {
                req.setAttribute("errorMessageKey", errorMessageKey);
                page = ConfigProvider.getInstance().getProperty("path.home");
            }
            return page;
        }
    },

    LOGOUT {
        @Override
        public String execute(HttpServletRequest req) {
            HttpSession session = req.getSession();
            String login = Optional.ofNullable((UserDto) session.getAttribute("user"))
                                   .map(UserDto::getLogin)
                                   .orElse("Unknown user");
            session.invalidate();
            log.info(login + " logged out");
            return ConfigProvider.getInstance().getProperty("path.home");
        }
    },

    CHANGE_LANG {
        @Override
        public String execute(HttpServletRequest req) {
            String langParam = req.getParameter("lang");
            if (langParam != null) {
                req.getSession().setAttribute("lang", langParam);
                log.info("Session language is set to " + langParam);
                return req.getParameter("sentFromPage");
            } else {
                return null;
            }
        }
    };

    private static final Logger log = LogManager.getLogger();

    public abstract String execute(HttpServletRequest req);
}
