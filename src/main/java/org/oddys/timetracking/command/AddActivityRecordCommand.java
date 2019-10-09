package org.oddys.timetracking.command;

import org.oddys.timetracking.service.ActivityRecordService;
import org.oddys.timetracking.service.ActivityRecordServiceImpl;
import org.oddys.timetracking.transaction.TransactionProxy;
import org.oddys.timetracking.util.ParameterValidator;
import org.oddys.timetracking.util.RequestParametersEncoder;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Map;

public class AddActivityRecordCommand implements Command {
    private static final Command INSTANCE = new AddActivityRecordCommand();
    private ActivityRecordService service = TransactionProxy.getInstance()
            .getProxy(ActivityRecordServiceImpl.getInstance());
    private final RequestParametersEncoder ENCODER;
    private final ParameterValidator VALIDATOR;

    private AddActivityRecordCommand() {
        ENCODER = RequestParametersEncoder.getInstance();
        VALIDATOR = ParameterValidator.getInstance();
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
        String path = ENCODER.encodeQueryParameters(
                "redirect:/time-tracking/cabinet/activity-records", parameters);
        if (!VALIDATOR.isValidAddActivityRecord(req)) {
            return req.getSession().getAttribute("messageKey") == null ? SC_BAD_REQUEST : path;
        }
        boolean added = service.addActivityRecord(
                LocalDate.parse(req.getParameter("date")),
                Long.parseLong(req.getParameter("duration")),
                Long.parseLong(req.getParameter("userActivityId")));
        String messageKey = added ? "activity.record.add.success"
                                   : "activity.record.add.fail";
        req.getSession().setAttribute("messageKey", messageKey);
        return path;
    }
}
