package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.dto.ActivityDto;
import org.oddys.timetracking.service.ActivityService;
import org.oddys.timetracking.service.ActivityServiceImpl;
import org.oddys.timetracking.service.ServiceException;
import org.oddys.timetracking.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowActivitiesCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
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
        try {
            List<ActivityDto> activities = service.findActivities(currentPage, rowsPerPage);
            req.getSession().setAttribute("activities", activities);
            req.getSession().setAttribute("currentPage", currentPage);
            req.getSession().setAttribute("rowsPerPage", rowsPerPage);
            req.getSession().setAttribute("numPages", service.getNumberOfPages(rowsPerPage));
            return ConfigManager.getInstance().getProperty("path.activities");
        } catch (ServiceException e) {
            LOGGER.error("ActivityService failed", e);
        }
        return null;
    }

    @Override
    public String toString() {
        return "ShowActivitiesCommand";
    }
}
