package org.oddys.timetracking.command;

import javax.servlet.http.HttpServletRequest;

public class ForwardCommand implements Command {
    private static final Command INSTANCE = new ForwardCommand();

    private ForwardCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        return req.getParameter("targetPage");
    }

    @Override
    public String toString() {
        return "ForwardCommand";
    }
}
