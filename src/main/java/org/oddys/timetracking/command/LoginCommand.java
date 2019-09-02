package org.oddys.timetracking.command;

import org.oddys.timetracking.dto.UserDto;
import org.oddys.timetracking.service.LoginService;
import org.oddys.timetracking.util.ConfigProvider;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private LoginService loginService = LoginService.getInstance();

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        UserDto user = loginService.logIn(req.getParameter("login"),
                                                        req.getParameter("password").toCharArray());
        if (user != null){
            req.getSession().setAttribute("user", user);
            page = ConfigProvider.getInstance().getProperty("path.cabinet");
        } else {
            req.setAttribute("errorMessageKey", "auth.error.notfound");
            page = ConfigProvider.getInstance().getProperty("path.home");
        }
        return page;
    }
}
