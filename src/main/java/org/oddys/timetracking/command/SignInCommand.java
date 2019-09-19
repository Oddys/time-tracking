package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.dto.UserDto;
import org.oddys.timetracking.service.ServiceException;
import org.oddys.timetracking.service.UserService;
import org.oddys.timetracking.service.UserServiceImpl;
import org.oddys.timetracking.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;

public class SignInCommand implements Command {
    private static final Logger log = LogManager.getLogger();
    private static final Command INSTANCE = new SignInCommand();
    private UserService service = UserServiceImpl.getInstance();

    private SignInCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        UserDto user = null;
        try {
            user = service.signIn(req.getParameter("login"),
                    req.getParameter("password").toCharArray());
        } catch (ServiceException e) {
            log.error("UserService failed to sign in", e);
            return null;
        }
        if (user != null){
            req.getSession().setAttribute("user", user);
            log.info(user.getLogin() + " signed in");
            return ConfigManager.getInstance().getProperty("path.cabinet");
        } else {
            req.setAttribute("errorMessageKey", "auth.error.notfound");
            return ConfigManager.getInstance().getProperty("path.home");
        }
    }
}
