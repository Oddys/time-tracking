package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.service.ActivityRecordService;
import org.oddys.timetracking.service.ActivityRecordServiceImpl;
import org.oddys.timetracking.service.ServiceException;
import org.oddys.timetracking.transaction.TransactionProxy;
import org.oddys.timetracking.util.ParameterValidator;

import javax.servlet.http.HttpServletRequest;

public class AddActivityRecordCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Command INSTANCE = new AddActivityRecordCommand();
    private ActivityRecordService service = TransactionProxy.getInstance()
            .getProxy(ActivityRecordServiceImpl.getInstance());

    private AddActivityRecordCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "ActivityRecordCommand";
    }

    @Override
    public String execute(HttpServletRequest req) {
        if (!ParameterValidator.getInstance().isValidAddActivityRecord(req)) {
            return req.getParameter("sentFromPage");
        }
        try {
            int numRowsAffected = service.addActivityRecord(req.getParameter("date"),
                    req.getParameter("duration"),
                    (Long) req.getSession().getAttribute("userActivityId"));
            if (numRowsAffected == 0) {
                req.setAttribute("errorMessage", "Record already exists");
            } else {
                req.setAttribute("successMessage", "Record added successfully");
            }
            return req.getParameter("sentFromPage");
        } catch (ServiceException e) {
            LOGGER.error("Failed to add ActivityRecord", e);
            return null;
        }


    }
}
