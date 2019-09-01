package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.dto.UserDto;
import org.oddys.timetracking.service.LoginService;
import org.oddys.timetracking.util.ConfigProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public enum Commands implements Command {
    LOGIN {
        @Override
        public String execute(HttpServletRequest req) {
            // TODO Rewrite to make method shorter
            String page = null;
            String errorMessageKey = null;
            if ("".equals(req.getParameter("login"))) {
                errorMessageKey = "auth.error.nologin";
            } else if ("".equals(req.getParameter("password"))) {
                errorMessageKey = "auth.error.nopassword";
            }
            if (errorMessageKey != null) {
                req.setAttribute("errorMessageKey", errorMessageKey);
                return ConfigProvider.getInstance().getProperty("path.home");
            }
            UserDto user = LoginService.getInstance().logIn(req.getParameter("login"),
                    req.getParameter("password").toCharArray());
            if (user != null){
                req.removeAttribute("errorMessageKey");
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

    LOGOUT {
        @Override
        public String execute(HttpServletRequest req) {
            HttpSession session = req.getSession();
            String login = ((UserDto) session.getAttribute("user")).getLogin();
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
