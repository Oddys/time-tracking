package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.dto.UserDto;
import org.oddys.timetracking.service.UserService;
import org.oddys.timetracking.service.UserServiceImpl;
import org.oddys.timetracking.util.ConfigManager;
import org.oddys.timetracking.util.ParameterValidator;

import javax.servlet.http.HttpServletRequest;

public class SignInCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Command INSTANCE = new SignInCommand();
    private UserService service = UserServiceImpl.getInstance();

    private SignInCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        if (!ParameterValidator.getInstance().isValidSignIn(req)) {
            return ConfigManager.getInstance().getProperty("path.home");
        }
        UserDto user = service.signIn(req.getParameter("login"),
                req.getParameter("password").toCharArray());
        if (user != null){
            req.getSession().setAttribute("user", user);
            LOGGER.info(String.format("%s %s (%s) signed in", user.getFirstName(),
                    user.getLastName(), user.getLogin()));
            return "redirect:/time-tracking/cabinet";
        } else {
            req.getSession().setAttribute("messageKey", "auth.error.notfound");
            return "redirect:" + req.getContextPath();
        }
    }
}
