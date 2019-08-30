package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public enum Command {
    LOGIN {
        @Override
        public String execute(HttpServletRequest req) {
            User user = new User("user1", "pass1");
            String page = null;
            if (user.getLogin().equals(req.getParameter("login"))
                    && user.getPassword().equals(req.getParameter("password"))) {
                req.getSession().setAttribute("user", user);
                log.info("User " + user.getLogin() + " logged in");
                page = "/WEB-INF/pages/cabinet.jsp";
            } else {
                log.info(req.getParameter("login") + " failed to log in");
                page = "/index.jsp";
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
            return "/index.jsp";
        }
    };

    private static final Logger log = LogManager.getLogger();

    public abstract String execute(HttpServletRequest req);
}
