package org.oddys.timetracking.command;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {
    private static final Command INSTANCE = new EmptyCommand();

    private EmptyCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        return req.getServletPath();
    }
}
