package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.service.ActivityService;
import org.oddys.timetracking.service.ActivityServiceImpl;
import org.oddys.timetracking.service.ServiceException;
import org.oddys.timetracking.transaction.TransactionProxy;
import org.oddys.timetracking.util.ConfigManager;
import org.oddys.timetracking.util.ParameterValidator;

import javax.servlet.http.HttpServletRequest;

public class AddActivityCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Command INSTANCE = new AddActivityCommand();
    private ActivityService service = TransactionProxy.getInstance().getProxy(
            ActivityServiceImpl.getInstance());

    private AddActivityCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        if (!ParameterValidator.getInstance().isValidAddActivity(req)) {
            return ConfigManager.getInstance().getProperty("path.activities");
        }
        String activityName = req.getParameter("activityName");
        try {
            if (service.addActivity(activityName)) {
                req.setAttribute("messageKey", "activity.add.success");
            } else {
                req.setAttribute("messageKey", "activity.add.fail");
            }
            req.setAttribute("activityName", activityName);
            return ConfigManager.getInstance().getProperty("path.activities");
        } catch (ServiceException e) {
            LOGGER.error("ActivityService failed to add Activity", e);
            return null;
        }
    }
}
