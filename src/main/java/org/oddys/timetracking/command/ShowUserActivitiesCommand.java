package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.dto.UserActivityDto;
import org.oddys.timetracking.service.SearchUserActivitiesService;
import org.oddys.timetracking.service.SearchUserActivitiesServiceImpl;
import org.oddys.timetracking.service.ServiceException;
import org.oddys.timetracking.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowUserActivitiesCommand implements Command {
    private static final Logger log = LogManager.getLogger();
    private static final ShowUserActivitiesCommand INSTANCE = new ShowUserActivitiesCommand();
    private SearchUserActivitiesService searchService = SearchUserActivitiesServiceImpl.getInstance();

    private ShowUserActivitiesCommand() {}

    public static ShowUserActivitiesCommand getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String userIdString = req.getParameter("userId");
        if (userIdString == null) {
            return ConfigManager.getInstance().getProperty(ConfigManager.HOME_PATH);
        }
        Long userId = Long.valueOf(userIdString);
        List<UserActivityDto> userActivityDTOs = null;
        try {
            userActivityDTOs = searchService.searchUserActivitiesByUserId(userId);
        } catch (ServiceException e) {
            log.error("SearchUserActivitiesService failed", e);
            return null;
        }
        req.getSession().setAttribute("userFirstName", req.getParameter("userFirstName"));
        req.getSession().setAttribute("userLastName", req.getParameter("userLastName"));
        req.getSession().setAttribute("userActivities", userActivityDTOs);
        return ConfigManager.getInstance().getProperty("path.user.activities");
    }

    @Override
    public String toString() {
        return "ShowUserActivitiesCommand";
    }
}
