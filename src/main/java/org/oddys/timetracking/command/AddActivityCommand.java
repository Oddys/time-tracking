package org.oddys.timetracking.command;

import org.oddys.timetracking.service.ActivityService;
import org.oddys.timetracking.service.ActivityServiceImpl;
import org.oddys.timetracking.transaction.TransactionProxy;
import org.oddys.timetracking.util.ParameterValidator;
import org.oddys.timetracking.util.RequestParametersEncoder;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class AddActivityCommand implements Command {
    private static final Command INSTANCE = new AddActivityCommand();
    private ActivityService service = TransactionProxy.getInstance().getProxy(
            ActivityServiceImpl.getInstance());
    private final RequestParametersEncoder ENCODER;

    private AddActivityCommand() {
        ENCODER = RequestParametersEncoder.getInstance();
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Map<String, String> parameters = Map.of(
                "command", "show_activities",
                "currentPage", "1",
                "rowsPerPage", req.getParameter("rowsPerPage"));
        String path =  ENCODER.encodeQueryParameters(
                "redirect:/time-tracking/cabinet/activities", parameters);
        if (!ParameterValidator.getInstance().isValidAddActivity(req)) {
            return path;
        }
        String activityName = req.getParameter("activityName");
        String messageKey = service.addActivity(activityName) ? "activity.add.success"
                                                              : "activity.add.fail";
        req.getSession().setAttribute("messageKey", messageKey);
        req.getSession().setAttribute("activityName", activityName);
        return path;
    }
}
