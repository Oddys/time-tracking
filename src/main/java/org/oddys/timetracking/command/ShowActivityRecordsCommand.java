package org.oddys.timetracking.command;

import org.oddys.timetracking.dto.ActivityRecordDto;
import org.oddys.timetracking.service.ActivityRecordService;
import org.oddys.timetracking.service.ActivityRecordServiceImpl;
import org.oddys.timetracking.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowActivityRecordsCommand implements Command {
    private static final Command INSTANCE = new ShowActivityRecordsCommand();
    private ActivityRecordService service = ActivityRecordServiceImpl.getInstance();

    private ShowActivityRecordsCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        long userActivityId = Long.parseLong(req.getParameter("userActivityId"));
        long currentPage = Long.parseLong(req.getParameter("currentPage")); // TODO Check for exceptions
        Boolean userActivityAssigned = Boolean.valueOf(req.getParameter("userActivityAssigned"));
        int rowsPerPage = Integer.parseInt(req.getParameter("rowsPerPage"));
        List<ActivityRecordDto> records = service.findActivityRecords(userActivityId, currentPage, rowsPerPage);
        req.getSession().setAttribute("activityRecords", records);  // TODO Move to a helper class
        req.getSession().setAttribute("numPages",
                service.getNumberOfPages(userActivityId, rowsPerPage));
        req.getSession().setAttribute("userActivityId", userActivityId);
        req.getSession().setAttribute("userActivityAssigned", userActivityAssigned);
        req.getSession().setAttribute("currentPage", currentPage);
        req.getSession().setAttribute("rowsPerPage", rowsPerPage);
        return ConfigManager.getInstance().getProperty("path.activity.records");
    }
}
