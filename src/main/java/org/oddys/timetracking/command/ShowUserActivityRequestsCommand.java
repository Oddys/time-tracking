package org.oddys.timetracking.command;

import org.oddys.timetracking.service.UserActivityService;
import org.oddys.timetracking.service.UserActivityServiceImpl;
import org.oddys.timetracking.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;

public class ShowUserActivityRequestsCommand implements Command {
    private static final Command INSTANCE = new ShowUserActivityRequestsCommand();
    private UserActivityService service = UserActivityServiceImpl.getInstance();

    private ShowUserActivityRequestsCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }
    @Override
    public String execute(HttpServletRequest req) {
        long currentPage = Long.parseLong(req.getParameter("currentPage"));
        int rowsPerPage = Integer.parseInt(req.getParameter("rowsPerPage"));
        req.getSession().setAttribute("currentPage", req.getParameter("currentPage"));
        req.getSession().setAttribute("userActivities",
                service.findAllStatusChangeRequested(currentPage, rowsPerPage));
        req.getSession().setAttribute("numPages",
                service.getNumberOfPagesStatusChangeRequested(rowsPerPage));
        return ConfigManager.getInstance().getProperty("path.user.activity.requests");
    }
}
