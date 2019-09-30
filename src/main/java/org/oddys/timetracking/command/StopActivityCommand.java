package org.oddys.timetracking.command;

import org.oddys.timetracking.service.UserActivityService;
import org.oddys.timetracking.service.UserActivityServiceImpl;
import org.oddys.timetracking.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;

public class StopActivityCommand implements Command {
    private static final Command INSTANCE = new StopActivityCommand();
    private UserActivityService service = UserActivityServiceImpl.getInstance();

    private StopActivityCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Long userActivityId = Long.valueOf(req.getParameter("userActivityId"));
        if (service.requestStatusChange(userActivityId)) {
            req.setAttribute("messageKey", "user.activity.request.success");
        } else {
            req.setAttribute("messageKey", "user.activity.request.fail");
        }
        return ConfigManager.getInstance().getProperty("path.user.activities");
    }
}
