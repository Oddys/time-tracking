package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final EmptyCommand INSTANCE = new EmptyCommand();

    private EmptyCommand() {}

    public static EmptyCommand getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
//        return ConfigManager.getInstance().getProperty("path.home");
        LOGGER.debug("Empty command path: " + req.getServletPath().replace("/controller", ""));
//        return req.getServletPath().replace("/controller", "");
        return req.getPathInfo();
    }
}
