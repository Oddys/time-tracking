package org.oddys.timetracking.command;

import org.oddys.timetracking.dto.UserActivitiesDto;
import org.oddys.timetracking.service.SearchUserActivitiesService;
import org.oddys.timetracking.service.SearchUserActivitiesServiceImpl;
import org.oddys.timetracking.transaction.TransactionProxy;
import org.oddys.timetracking.util.ParameterValidator;

import javax.servlet.http.HttpServletRequest;

public class ShowUserActivitiesCommand implements Command {
    private static final Command INSTANCE = new ShowUserActivitiesCommand();
    private SearchUserActivitiesService searchService = TransactionProxy.getInstance()
            .getProxy(SearchUserActivitiesServiceImpl.getInstance());

    private ShowUserActivitiesCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        if (!ParameterValidator.getInstance().isValidShowUserActivities(req)) {
            return "redirect:/time-tracking/cabinet";
        }
        Long userId = Long.valueOf(req.getParameter("userId"));
        UserActivitiesDto dto = searchService.searchUserActivitiesByUserId(userId);
        if (dto.isEmpty()) {
            req.getSession().setAttribute("messageKey", "error.parameter.invalid");
            return "redirect:/time-tracking/cabinet";
        }
        req.setAttribute("userActivities", dto);
        return "/cabinet/user-activities";
    }
}
