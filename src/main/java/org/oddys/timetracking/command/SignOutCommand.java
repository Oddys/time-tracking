package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.dto.UserDto;
import org.oddys.timetracking.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class SignOutCommand implements Command {
    private static final Logger log = LogManager.getLogger();
    private static final Command INSTANCE = new SignOutCommand();

    private SignOutCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String login = Optional.ofNullable((UserDto) session.getAttribute("user"))
                .map(UserDto::getLogin)
                .orElse("Unknown user");
        session.invalidate();
        log.info(login + " signed out");
        return ConfigManager.getInstance().getProperty("path.home");
    }
}
