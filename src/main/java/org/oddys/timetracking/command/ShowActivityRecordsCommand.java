package org.oddys.timetracking.command;

import org.oddys.timetracking.dto.ActivityRecordsPage;
import org.oddys.timetracking.service.ActivityRecordService;
import org.oddys.timetracking.service.ActivityRecordServiceImpl;
import org.oddys.timetracking.transaction.TransactionProxy;
import org.oddys.timetracking.util.ParameterValidator;

import javax.servlet.http.HttpServletRequest;

public class ShowActivityRecordsCommand implements Command {
    private static final Command INSTANCE = new ShowActivityRecordsCommand();
    private ActivityRecordService service = TransactionProxy.getInstance()
            .getProxy(ActivityRecordServiceImpl.getInstance());
    private final ParameterValidator VALIDATOR = ParameterValidator.getInstance();

    private ShowActivityRecordsCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        if (!VALIDATOR.isValidShowActivityRecords(req)) {
            return SC_BAD_REQUEST;
        }
        ActivityRecordsPage page = service.findActivityRecords(
                Long.parseLong(req.getParameter("userActivityId")),
                Long.parseLong(req.getParameter("currentPage")),
                Integer.parseInt(req.getParameter("rowsPerPage")));
        if (page.isEmpty()) {
            return SC_BAD_REQUEST;
        }
        req.setAttribute("activityRecords", page);
        return "/cabinet/activity-records";
    }
}
