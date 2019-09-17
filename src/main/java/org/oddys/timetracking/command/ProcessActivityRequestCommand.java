package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.service.ServiceException;
import org.oddys.timetracking.service.UserActivityService;
import org.oddys.timetracking.service.UserActivityServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class ProcessActivityRequestCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Command INSTANCE = new ProcessActivityRequestCommand();
    private UserActivityService service = UserActivityServiceImpl.getInstance();

    private ProcessActivityRequestCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        long userActivityId = Long.parseLong(req.getParameter("userActivityId"));
        boolean currentAssigned = Boolean.parseBoolean(req.getParameter("currentAssigned"));
        try {
            service.processActivityRequest(userActivityId, currentAssigned);  // TODO Add status check on DAO level
            req.setAttribute("messageKey", "Status of User Activity successfully changed");
            return "/controller?command=show_activity_requests&currentPage=1&rowsPerPage=5";
        } catch (ServiceException e) {
            LOGGER.error("UserActivityService failed", e);
            return null;
        }
    }
}
