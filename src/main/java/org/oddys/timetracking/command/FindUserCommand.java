package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.dto.UserDto;
import org.oddys.timetracking.service.FindUserService;
import org.oddys.timetracking.service.FindUserServiceImpl;
import org.oddys.timetracking.service.ServiceException;
import org.oddys.timetracking.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindUserCommand implements Command {
    private static final Logger log = LogManager.getLogger();
    private static final FindUserCommand INSTANCE = new FindUserCommand();
    private FindUserService findUserService = FindUserServiceImpl.getInstance();

    private FindUserCommand() {}

    public static FindUserCommand getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        List<UserDto> users = null;
        String lastName = req.getParameter("lastName");
        try {
            users = findUserService.search(lastName);
        } catch (ServiceException e) {
            log.error("Searching User by last name failed", e);
            return null;
        }
        if (users.isEmpty()) {
            req.getSession().setAttribute("message", "No user with last name " + lastName + " found" ); // FIXME
        }
        req.getSession().setAttribute("users", users);
        return ConfigManager.getInstance().getProperty(ConfigManager.USERS_LIST_PATH);
    }

    @Override
    public String toString() {
        return "FindUserCommand";
    }
}
