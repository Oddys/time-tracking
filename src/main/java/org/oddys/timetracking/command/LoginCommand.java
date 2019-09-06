package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.dto.UserDto;
import org.oddys.timetracking.service.LoginService;
import org.oddys.timetracking.service.LoginServiceImpl;
import org.oddys.timetracking.util.ConfigManager;
import org.oddys.timetracking.transaction.TransactionProxy;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private static final Logger log = LogManager.getLogger();
    private static final LoginCommand INSTANCE = new LoginCommand();
    private LoginService loginService = TransactionProxy.getInstance()
            .getProxy(LoginServiceImpl.getInstance());

    private LoginCommand() {}

    public static LoginCommand getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        UserDto user = loginService.logIn(req.getParameter("login"),
                req.getParameter("password").toCharArray());
        if (user != null){
            req.getSession().setAttribute("user", user);
            log.info(user.getLogin() + " signed in");
            return ConfigManager.getInstance().getProperty(ConfigManager.CABINET_PATH);
        } else {
            req.setAttribute("errorMessageKey", "auth.error.notfound");
            return ConfigManager.getInstance().getProperty(ConfigManager.HOME_PATH);
        }
    }

    @Override
    public String toString() {
        return "LoginCommand";
    }
}
