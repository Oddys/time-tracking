package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.dto.UserDto;
import org.oddys.timetracking.service.LoginService;
import org.oddys.timetracking.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private static final LoginCommand INSTANCE = new LoginCommand();
    private static final Logger log = LogManager.getLogger();
    private final LoginService LOGIN_SERVICE;

    private LoginCommand() {
        LOGIN_SERVICE = LoginService.getInstance();
    }

    public static LoginCommand getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        UserDto user = LOGIN_SERVICE.logIn(
                req.getParameter("login"), req.getParameter("password").toCharArray());
        if (user != null){
            req.getSession().setAttribute("user", user);
            log.info(user.getLogin() + " signed in");
            page = ConfigManager.getInstance().getProperty(ConfigManager.CABINET_PATH);
        } else {
            req.setAttribute("errorMessageKey", "auth.error.notfound");
            page = ConfigManager.getInstance().getProperty(ConfigManager.HOME_PATH);
        }
        return page;
    }

    @Override
    public String toString() {
        return "LoginCommand";
    }
}
