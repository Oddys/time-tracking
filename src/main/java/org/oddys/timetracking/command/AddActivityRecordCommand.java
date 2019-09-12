package org.oddys.timetracking.command;

import org.oddys.timetracking.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;

public class AddActivityRecordCommand implements Command {
    private static final Command INSTANCE = new AddActivityRecordCommand();

    private AddActivityRecordCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        if ("GET".equals(req.getMethod())) {
            req.getSession().setAttribute("userActivityId", req.getParameter("userActivityId"));
            return ConfigManager.getInstance().getProperty("path.activity.add");
        }
        return null;
    }
}
