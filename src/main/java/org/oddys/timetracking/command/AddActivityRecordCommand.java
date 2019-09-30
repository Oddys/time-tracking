package org.oddys.timetracking.command;

import org.oddys.timetracking.service.ActivityRecordService;
import org.oddys.timetracking.service.ActivityRecordServiceImpl;
import org.oddys.timetracking.transaction.TransactionProxy;
import org.oddys.timetracking.util.ConfigManager;
import org.oddys.timetracking.util.ParameterValidator;

import javax.servlet.http.HttpServletRequest;

public class AddActivityRecordCommand implements Command {
    private static final Command INSTANCE = new AddActivityRecordCommand();
    private ActivityRecordService service = TransactionProxy.getInstance()
            .getProxy(ActivityRecordServiceImpl.getInstance());

    private AddActivityRecordCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        if (!ParameterValidator.getInstance().isValidAddActivityRecord(req)) {
            return ConfigManager.getInstance().getProperty("path.activity.records");
        }
        int numRowsAffected = service.addActivityRecord(  // FIXME Make it return boolean
                req.getParameter("date"),
                req.getParameter("duration"),
                (Long) req.getSession().getAttribute("userActivityId"));
        if (numRowsAffected == 0) {
            req.setAttribute("messageKey", "activity.record.add.fail");
        } else {
            req.setAttribute("messageKey", "activity.record.add.success");
        }
        return String.format(ConfigManager.getInstance().getProperty(
                "path.controller.activity.records.format"),  // FIXME Consider to move to a helper class
                req.getSession().getAttribute("userActivityId"),
                req.getSession().getAttribute("userActivityAssigned"),
                req.getSession().getAttribute("rowsPerPage"));


    }
}
