package org.oddys.timetracking.command;

import org.oddys.timetracking.dto.ActivityRecordsPage;
import org.oddys.timetracking.service.ActivityRecordService;
import org.oddys.timetracking.service.ActivityRecordServiceImpl;
import org.oddys.timetracking.transaction.TransactionProxy;

import javax.servlet.http.HttpServletRequest;

public class ShowActivityRecordsCommand implements Command {
    private static final Command INSTANCE = new ShowActivityRecordsCommand();
    private ActivityRecordService service = TransactionProxy.getInstance()
            .getProxy(ActivityRecordServiceImpl.getInstance());

    private ShowActivityRecordsCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        long userActivityId;
        long currentPage;
        int rowsPerPage;
        try {
            userActivityId = Long.parseLong(req.getParameter("userActivityId"));
            currentPage = Long.parseLong(req.getParameter("currentPage"));
            rowsPerPage = Integer.parseInt(req.getParameter("rowsPerPage"));
        } catch (NumberFormatException e) {
            return SC_BAD_REQUEST;
        }
        ActivityRecordsPage page = service.findActivityRecords(userActivityId, currentPage, rowsPerPage);
        if (page.isEmpty()) {
            return SC_BAD_REQUEST;
        }
        req.setAttribute("activityRecords", page);
        return "/cabinet/activity-records";
    }
}
