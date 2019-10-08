package org.oddys.timetracking.command;

import org.oddys.timetracking.service.UserActivityService;
import org.oddys.timetracking.service.UserActivityServiceImpl;
import org.oddys.timetracking.util.RequestParametersEncoder;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class StopActivityCommand implements Command {
    private static final Command INSTANCE = new StopActivityCommand();
    private UserActivityService service = UserActivityServiceImpl.getInstance();
    private final RequestParametersEncoder encoder;

    private StopActivityCommand() {
        encoder = RequestParametersEncoder.getInstance();
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Long userActivityId = Long.valueOf(req.getParameter("userActivityId"));
        String messageKey = service.requestStatusChange(userActivityId)
                ? "user.activity.stop.success"
                : "user.activity.stop.fail";
        req.getSession().setAttribute("messageKey", messageKey);
        Map<String, String> parameters = Map.of(
                "userId", req.getParameter("userId"),
                "command", "show_user_activities");
        return encoder.encodeQueryParameters(
                "redirect:/time-tracking/cabinet/user-activities",
                parameters);
    }
}
