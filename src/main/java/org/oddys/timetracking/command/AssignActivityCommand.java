package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.service.ServiceException;
import org.oddys.timetracking.service.UserActivityService;
import org.oddys.timetracking.service.UserActivityServiceImpl;
import org.oddys.timetracking.transaction.TransactionProxy;
import org.oddys.timetracking.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;

public class AssignActivityCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Command INSTANCE = new AssignActivityCommand();
    private UserActivityService service = TransactionProxy.getInstance().getProxy(
            UserActivityServiceImpl.getInstance());

    private AssignActivityCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
//        Long userId = ((UserDto) req.getSession().getAttribute("user")).getUserId();
        Long userId = Long.valueOf(req.getParameter("userId"));
        Long activityId = Long.valueOf(req.getParameter("activityId"));
        try {
            if (service.assignActivity(userId, activityId)) {
                req.setAttribute("messageKey", "user.activities.assigned.success");
            } else {
                req.setAttribute("messageKey", "user.activities.assigned.fail");
            }
            req.setAttribute("activityName", req.getParameter("activityName"));
            return ConfigManager.getInstance().getProperty("path.activities");
        } catch (ServiceException e) {
            LOGGER.error("UserActivityService failed", e);
            return null;
        }
    }
}