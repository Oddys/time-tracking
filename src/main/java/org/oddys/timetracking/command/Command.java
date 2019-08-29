package org.oddys.timetracking.command;

import org.oddys.timetracking.entity.User;

import javax.servlet.http.HttpServletRequest;

public enum Command {
    LOGIN {
        @Override
        public String execute(HttpServletRequest req) {
            User user = new User("user1", "pass1");
            String page = null;
            if (user.getLogin().equals(req.getParameter("login"))
                    && user.getPassword().equals(req.getParameter("password"))) {
                req.getSession().setAttribute("user", user);
                page = "pages/cabinet.jsp";
            } else {
                page = "pages/login.jsp";
            }
            return page;
        }
    },

    LOGOUT {
        @Override
        public String execute(HttpServletRequest req) {
            req.getSession().invalidate();
            return "pages/login.jsp";
        }
    },

    DEFAULT {
        @Override
        public String execute(HttpServletRequest req) {
            return null;
        }
    };

    public abstract String execute(HttpServletRequest req);
}
