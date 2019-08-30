package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.entity.Role;
import org.oddys.timetracking.entity.User;
import org.oddys.timetracking.util.ConfigProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public enum Command {
    LOGIN {
        @Override
        public String execute(HttpServletRequest req) {
            User user = new User("user1", "pass1", Role.USER);
            String page = null;
            if (user.getLogin().equals(req.getParameter("login"))
                    && user.getPassword().equals(req.getParameter("password"))) {
                req.getSession().setAttribute("user", user);
                log.info("User " + user.getLogin() + " logged in");
                page = ConfigProvider.getProperty("path.cabinet");
            } else {
                log.info(req.getParameter("login") + " failed to log in");
                page = ConfigProvider.getProperty("path.home");
            }
            return page;
        }
    },

    LOGOUT {
        @Override
        public String execute(HttpServletRequest req) {
            HttpSession session = req.getSession();
            String login = ((User) session.getAttribute("user")).getLogin();
            session.invalidate();
            log.info(login + " logged out");
            return ConfigProvider.getProperty("path.home");
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
