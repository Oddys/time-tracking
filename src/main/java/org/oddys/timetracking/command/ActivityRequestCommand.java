package org.oddys.timetracking.command;

import javax.servlet.http.HttpServletRequest;

public class ActivityRequestCommand implements Command {
    private static final Command INSTANCE = new ActivityRequestCommand();

    private ActivityRequestCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        return null;
    }
}
