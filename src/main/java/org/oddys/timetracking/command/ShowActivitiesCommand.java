package org.oddys.timetracking.command;

import org.oddys.timetracking.dto.ActivityDto;
import org.oddys.timetracking.service.ActivityService;
import org.oddys.timetracking.service.ActivityServiceImpl;
import org.oddys.timetracking.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowActivitiesCommand implements Command {
    private static final Command INSTANCE = new ShowActivitiesCommand();
    private ActivityService service = ActivityServiceImpl.getInstance();

    private ShowActivitiesCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        long currentPage = Long.parseLong(req.getParameter("currentPage"));
        int rowsPerPage = Integer.parseInt(req.getParameter("rowsPerPage"));
        List<ActivityDto> activities = service.findActivities(currentPage, rowsPerPage);
        req.getSession().setAttribute("activities", activities);  // TODO Move to a helper class
        req.getSession().setAttribute("currentPage", currentPage);
        req.getSession().setAttribute("rowsPerPage", rowsPerPage);
        req.getSession().setAttribute("numPages", service.getNumberOfPages(rowsPerPage));
        return ConfigManager.getInstance().getProperty("path.activities");
    }
}
