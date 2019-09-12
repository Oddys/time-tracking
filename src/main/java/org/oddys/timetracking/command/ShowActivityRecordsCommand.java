package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.service.ActivityRecordService;
import org.oddys.timetracking.service.ActivityRecordServiceImpl;
import org.oddys.timetracking.service.ServiceException;
import org.oddys.timetracking.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;

public class ShowActivityRecordsCommand implements Command {
    private static final Logger log = LogManager.getLogger();
    private static final Command INSTANCE = new ShowActivityRecordsCommand();
    private ActivityRecordService service = ActivityRecordServiceImpl.getInstance();

    private ShowActivityRecordsCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        long currentPage = Long.parseLong(req.getParameter("currentPage")); // TODO Check for exceptions
        int recordsPerPage = Integer.parseInt(req.getParameter("recordsPerPage"));
        try {
            req.setAttribute("activityRecords", service.findActivityRecords(currentPage, recordsPerPage));
            long numRows =  service.getNumberOfRows();
            long numPages = numRows / recordsPerPage;
            if (numRows / recordsPerPage != 0) {
                numPages++;
            }
            req.setAttribute("numPages", numPages);
            return ConfigManager.getInstance().getProperty("path.activity.records");
        } catch (ServiceException e) {
            log.error("ActivityRecordService failed to obtain the number of rows", e);  // FIXME
            return null;
        }
    }
}
