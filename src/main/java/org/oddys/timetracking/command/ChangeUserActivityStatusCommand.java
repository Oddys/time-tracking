package org.oddys.timetracking.command;

import org.oddys.timetracking.service.UserActivityService;
import org.oddys.timetracking.service.UserActivityServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class ChangeUserActivityStatusCommand implements Command {
    private static final Command INSTANCE = new ChangeUserActivityStatusCommand();
    private UserActivityService service = UserActivityServiceImpl.getInstance();

    private ChangeUserActivityStatusCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        long userActivityId = Long.parseLong(req.getParameter("userActivityId"));
        boolean currentAssigned = Boolean.parseBoolean(req.getParameter("currentAssigned"));
        service.changeUserActivityStatus(userActivityId, currentAssigned);  // TODO Add status check on DAO level
        req.setAttribute("messageKey", "user.activity.status.changed");
        return "/controller?command=show_activity_requests&currentPage=1&rowsPerPage=5";
    }
}
