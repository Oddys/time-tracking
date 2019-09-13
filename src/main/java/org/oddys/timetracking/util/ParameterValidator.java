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
            request.setAttribute("errorMessage", "Please, enter the date");
            return false;
        }
        if (StringUtils.isBlank(request.getParameter("duration"))) {
            request.setAttribute("errorMessage", "Please, enter the duration");
            return false;
        }
        return true;
    }
}
