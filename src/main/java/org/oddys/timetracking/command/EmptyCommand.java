package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        LOGGER.debug("Empty command path: " + req.getServletPath());
        return req.getServletPath();
    }
}
