package org.oddys.timetracking.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class ParameterValidator {
    private static final ParameterValidator INSTANCE = new ParameterValidator();

    private ParameterValidator() {}

    public static ParameterValidator getInstance() {
        return INSTANCE;
    }

    public boolean isValidAddActivityRecord(HttpServletRequest request) {
        if (StringUtils.isBlank(request.getParameter("date"))) {
            request.setAttribute("messageKey", "param.empty.date");
            return false;
        }
        if (StringUtils.isBlank(request.getParameter("duration"))) {
            request.setAttribute("messageKey", "param.empty.duration");
            return false;
        }
        return true;
    }

    public boolean isValidAddUser(HttpServletRequest request) {
        if (StringUtils.isBlank(request.getParameter("login"))) {
            request.setAttribute("messageKey", "Please, enter your login");
            return false;
        }
        if (StringUtils.isBlank(request.getParameter("password"))) {
            request.setAttribute("messageKey", "Please, enter your password");
            return false;
        }
        if (StringUtils.isBlank(request.getParameter("firstName"))) {
            request.setAttribute("messageKey", "Please, enter your first name");
            return false;
        }
        if (StringUtils.isBlank(request.getParameter("lastName"))) {
            request.setAttribute("messageKey", "Please, enter your last name");
            return false;
        }
        return true;
    }

    public boolean isValidAddActivity(HttpServletRequest request) {
        if (StringUtils.isBlank(request.getParameter("activityName"))) {
            request.setAttribute("messageKey", "param.activity.name");
            return false;
        }
        return true;
    }

    public boolean isValidSignIn(HttpServletRequest request) {
        if (StringUtils.isBlank(request.getParameter("login"))) {
            request.setAttribute("messageKey", "auth.error.nologin");
            return false;
        }
        if (StringUtils.isBlank(request.getParameter("password"))) {
            request.setAttribute("messageKey", "auth.error.nopassword");
            return false;
        }
        return true;
    }

    public boolean isValidPage(HttpServletRequest request) {
        try {
            long currentPage = Long.parseLong(request.getParameter("currentPage"));
            int rowsPerPage = Integer.parseInt(request.getParameter("rowsPerPage"));
            return currentPage > 0 && rowsPerPage > 0 && rowsPerPage < 50;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isValidShowUserActivities(HttpServletRequest request) {
        try {
            Long.parseLong(request.getParameter("userId"));
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("messageKey", "error.parameter.invalid");
            return false;
        }
        return true;
    }
}
