package org.oddys.timetracking.command;

import org.oddys.timetracking.dto.ActivityRecordDto;
import org.oddys.timetracking.dto.ActivityRecordsPage;
import org.oddys.timetracking.service.ActivityRecordService;
import org.oddys.timetracking.service.ActivityRecordServiceImpl;
import org.oddys.timetracking.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        long userActivityId = 0;
        long currentPage = 0; // TODO Check for exceptions
        Boolean userActivityAssigned = null;
        int rowsPerPage = 0;
        try {
            userActivityId = Long.parseLong(req.getParameter("userActivityId"));
            currentPage = Long.parseLong(req.getParameter("currentPage"));
            userActivityAssigned = Boolean.valueOf(req.getParameter("userActivityAssigned"));
            rowsPerPage = Integer.parseInt(req.getParameter("rowsPerPage"));
        } catch (NumberFormatException e) {
            return "SC=" + HttpServletResponse.SC_BAD_REQUEST;
        }
        ActivityRecordsPage page = service.findActivityRecords(userActivityId, currentPage, rowsPerPage);
        page.setAssigned(userActivityAssigned);
        req.setAttribute("activityRecords", page);
//        List<ActivityRecordDto> records = service.findActivityRecords(userActivityId, currentPage, rowsPerPage);
//        req.getSession().setAttribute("activityRecords", records);  // TODO Move to a helper class
//        req.getSession().setAttribute("numPages",
//                service.getNumberOfPages(userActivityId, rowsPerPage));
//        req.getSession().setAttribute("userActivityId", userActivityId);
//        req.getSession().setAttribute("userActivityAssigned", userActivityAssigned);
//        req.getSession().setAttribute("currentPage", currentPage);
//        req.getSession().setAttribute("rowsPerPage", rowsPerPage);
        return "/activity-records";
    }
}
