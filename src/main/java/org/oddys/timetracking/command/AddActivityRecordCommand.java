package org.oddys.timetracking.command;

import org.oddys.timetracking.service.ActivityRecordService;
import org.oddys.timetracking.service.ActivityRecordServiceImpl;
import org.oddys.timetracking.transaction.TransactionProxy;
import org.oddys.timetracking.util.ParameterValidator;
import org.oddys.timetracking.util.RequestParametersEncoder;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class AddActivityRecordCommand implements Command {
    private static final Command INSTANCE = new AddActivityRecordCommand();
    private ActivityRecordService service = TransactionProxy.getInstance()
            .getProxy(ActivityRecordServiceImpl.getInstance());
    private final RequestParametersEncoder encoder;

    private AddActivityRecordCommand() {
        encoder = RequestParametersEncoder.getInstance();
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Map<String, String> parameters = Map.of(
                "userActivityId", req.getParameter("userActivityId"),
                "rowsPerPage", req.getParameter("rowsPerPage"),
                "currentPage", "1",
                "command", "show_activity_records"
        );
        String path = encoder.encodeQueryParameters(
                "redirect:/time-tracking/cabinet/activity-records", parameters);
        if (!ParameterValidator.getInstance().isValidAddActivityRecord(req)) {
            return path;
        }
        boolean added = service.addActivityRecord(req.getParameter("date"),
                req.getParameter("duration"), req.getParameter("userActivityId"));
        String messageKey = added ? "activity.record.add.success"
                                   : "activity.record.add.fail";
        req.getSession().setAttribute("messageKey", messageKey);
        return path;
    }
}
