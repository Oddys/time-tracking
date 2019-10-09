package org.oddys.timetracking.command;

import org.oddys.timetracking.service.UserActivityService;
import org.oddys.timetracking.service.UserActivityServiceImpl;
import org.oddys.timetracking.transaction.TransactionProxy;
import org.oddys.timetracking.util.ParameterValidator;
import org.oddys.timetracking.util.RequestParametersEncoder;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ChangeUserActivityStatusCommand implements Command {
    private static final Command INSTANCE = new ChangeUserActivityStatusCommand();
    private UserActivityService service = TransactionProxy.getInstance()
            .getProxy(UserActivityServiceImpl.getInstance());
    private final ParameterValidator VALIDATOR = ParameterValidator.getInstance();
    private final RequestParametersEncoder ENCODER = RequestParametersEncoder.getInstance();

    private ChangeUserActivityStatusCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        if (!VALIDATOR.isValidChangeUserActivityStatus(req)) {
            return SC_BAD_REQUEST;
        }
        long userActivityId = Long.parseLong(req.getParameter("userActivityId"));
        boolean currentAssigned = Boolean.parseBoolean(req.getParameter("currentAssigned"));
        if (!service.changeStatus(userActivityId, currentAssigned)) {
            return SC_BAD_REQUEST;
        }
        req.getSession().setAttribute("messageKey", "user.activity.status.changed");
        Map<String, String> parameters = Map.of(
                "command", "show_activity_requests",
                "currentPage", "1",
                "rowsPerPage", req.getParameter("rowsPerPage"));
        return ENCODER.encodeQueryParameters(
                "redirect:/time-tracking/cabinet/show-activity-requests", parameters);
    }
}
