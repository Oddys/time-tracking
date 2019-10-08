package org.oddys.timetracking.command;

import org.oddys.timetracking.dto.PageDto;
import org.oddys.timetracking.dto.UserActivityDto;
import org.oddys.timetracking.service.UserActivityService;
import org.oddys.timetracking.service.UserActivityServiceImpl;
import org.oddys.timetracking.util.ParameterValidator;

import javax.servlet.http.HttpServletRequest;

public class ShowUserActivityRequestsCommand implements Command {
    private static final Command INSTANCE = new ShowUserActivityRequestsCommand();
    private UserActivityService service = UserActivityServiceImpl.getInstance();
    private final ParameterValidator VALIDATOR = ParameterValidator.getInstance();

    private ShowUserActivityRequestsCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        if (!VALIDATOR.isValidPage(req)) {
            return SC_BAD_REQUEST;
        }
        long currentPage = Long.parseLong(req.getParameter("currentPage"));
        int rowsPerPage = Integer.parseInt(req.getParameter("rowsPerPage"));
        PageDto<UserActivityDto> page = service.findAllStatusChangeRequested(
                currentPage, rowsPerPage);
        req.setAttribute("userActivities", page);
        return "/cabinet/user-activity-requests";
    }
}
