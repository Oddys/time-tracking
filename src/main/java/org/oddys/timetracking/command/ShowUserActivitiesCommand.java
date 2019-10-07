package org.oddys.timetracking.command;

import org.oddys.timetracking.dto.UserActivityDto;
import org.oddys.timetracking.service.SearchUserActivitiesService;
import org.oddys.timetracking.service.SearchUserActivitiesServiceImpl;
import org.oddys.timetracking.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowUserActivitiesCommand implements Command {
    private static final Command INSTANCE = new ShowUserActivitiesCommand();
    private SearchUserActivitiesService searchService = SearchUserActivitiesServiceImpl.getInstance();

    private ShowUserActivitiesCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

//    @Override
//    public String execute(HttpServletRequest req) {
//        String userIdString = req.getParameter("userId");
//        if (userIdString == null) {
//            return ConfigManager.getInstance().getProperty("path.home");
//        }
//        Long userId = Long.valueOf(userIdString);
//        List<UserActivityDto> userActivityDTOs = searchService.searchUserActivitiesByUserId(userId);
//        req.getSession().setAttribute("userFirstName", req.getParameter("userFirstName"));
//        req.getSession().setAttribute("userLastName", req.getParameter("userLastName"));
//        req.getSession().setAttribute("userActivities", userActivityDTOs);
//        return ConfigManager.getInstance().getProperty("path.user.activities");
//    }

    @Override
    public String execute(HttpServletRequest req) {
        String userIdString = req.getParameter("userId");  // TODO Add validation
        if (userIdString == null) {
            return ConfigManager.getInstance().getProperty("path.home");
        }
        Long userId = Long.valueOf(userIdString);
        List<UserActivityDto> userActivityDTOs = searchService.searchUserActivitiesByUserId(userId);
        req.getSession().setAttribute("userFirstName", req.getParameter("userFirstName"));
        req.getSession().setAttribute("userLastName", req.getParameter("userLastName"));
        req.getSession().setAttribute("userActivities", userActivityDTOs);
        return "/user-activities";
    }
}
