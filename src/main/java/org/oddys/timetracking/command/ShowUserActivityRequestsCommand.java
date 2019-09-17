package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.service.ServiceException;
import org.oddys.timetracking.service.UserActivityService;
import org.oddys.timetracking.service.UserActivityServiceImpl;
import org.oddys.timetracking.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;

public class ShowUserActivityRequestsCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Command INSTANCE = new ShowUserActivityRequestsCommand();
    private UserActivityService service = UserActivityServiceImpl.getInstance();

    private ShowUserActivityRequestsCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }
    @Override
    public String execute(HttpServletRequest req) {
        try {
            req.getSession().setAttribute("userActivities", service.findAllStatusChangeRequested());
            return ConfigManager.getInstance().getProperty("path.user.activity.requests");
        } catch (ServiceException e) {
            LOGGER.error("UserActivityService failed", e);
            return null;
        }
    }
}
