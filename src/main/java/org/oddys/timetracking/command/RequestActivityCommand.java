package org.oddys.timetracking.command;

import org.oddys.timetracking.service.UserActivityService;
import org.oddys.timetracking.service.UserActivityServiceImpl;
import org.oddys.timetracking.transaction.TransactionProxy;
import org.oddys.timetracking.util.RequestParametersEncoder;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class RequestActivityCommand implements Command {
    private static final Command INSTANCE = new RequestActivityCommand();
    private UserActivityService service = TransactionProxy.getInstance().getProxy(
            UserActivityServiceImpl.getInstance());
    private final RequestParametersEncoder ENCODER;

    private RequestActivityCommand() {
        ENCODER = RequestParametersEncoder.getInstance();
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Long userId = Long.valueOf(req.getParameter("userId"));
        Long activityId = Long.valueOf(req.getParameter("activityId"));
        String messageKey = service.assignActivity(userId, activityId)
                ? "user.activity.request.success"
                : "user.activity.request.fail";
        req.getSession().setAttribute("messageKey", messageKey);
        req.getSession().setAttribute("activityName", req.getParameter("activityName"));
        Map<String, String> parameters = Map.of(
                "command", "show_activities",
                "currentPage", "1",
                "rowsPerPage", req.getParameter("rowsPerPage"));
        return ENCODER.encodeQueryParameters(
                "redirect:/time-tracking/cabinet/activities", parameters);
    }
}
