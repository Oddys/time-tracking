package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.dto.UserActivityDto;
import org.oddys.timetracking.dto.UserDto;
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
        UserDto userDto = ((UserDto) req.getSession().getAttribute("user"));
        List<UserActivityDto> userActivityDTOs = null;
        try {
            userActivityDTOs = searchService.searchUserActivitiesByUserId(userDto.getUserId());
        } catch (ServiceException e) {
            log.error("SearchUserActivitiesService failed", e);
            return null;
        }
        req.getSession().setAttribute("userActivities", userActivityDTOs);
        return ConfigManager.getInstance().getProperty("path.user.activities");
    }
}
