package org.oddys.timetracking.command;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.dto.UserDto;
import org.oddys.timetracking.service.UserService;
import org.oddys.timetracking.service.UserServiceImpl;
import org.oddys.timetracking.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindUserCommand implements Command {
    private static final FindUserCommand INSTANCE = new FindUserCommand();
    private UserService service = UserServiceImpl.getInstance();

    private FindUserCommand() {}

    public static FindUserCommand getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String lastName = req.getParameter("lastName");
        if (StringUtils.isBlank(lastName)) {
            return ConfigManager.getInstance().getProperty("path.home");
        }
        List<UserDto> users = service.search(lastName);
        if (users.isEmpty()) {
            req.setAttribute("userNotFound", true);
        }
        req.getSession().setAttribute("users", users);
        return ConfigManager.getInstance().getProperty(ConfigManager.USERS_LIST_PATH);
    }
}
