package org.oddys.timetracking.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ParameterValidator {
    private static final ParameterValidator INSTANCE = new ParameterValidator();
    private static final Set<String> VALID_BOOL_STRINGS = Set.of("true", "false");

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

    public Map<String, Boolean> validateAddUser(HttpServletRequest request) {
        Map<String, Boolean> errors = new HashMap<>();
        if (StringUtils.isBlank(request.getParameter("login"))) {
            errors.put("login", true);
        }
        if (StringUtils.isBlank(request.getParameter("password"))) {
            errors.put("password", true);
        }
        if (StringUtils.isBlank(request.getParameter("firstName"))) {
            errors.put("firstName", true);
        }
        if (StringUtils.isBlank(request.getParameter("lastName"))) {
            errors.put("lastName", true);
        }
        return errors;
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

    public boolean isValidChangeUserActivityStatus(HttpServletRequest request) {
        try {
            Long.parseLong(request.getParameter("userActivityId"));
        } catch (NumberFormatException e) {
            return false;
        }
        return VALID_BOOL_STRINGS.contains(request.getParameter("currentAssigned"));
    }

    public boolean isValidShowActivityRecords(HttpServletRequest request) {
        try {
            Long.parseLong(request.getParameter("userActivityId"));
            Long.parseLong(request.getParameter("currentPage"));
            Integer.parseInt(request.getParameter("rowsPerPage"));
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
