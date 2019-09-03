package org.oddys.timetracking.command;

import org.oddys.timetracking.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {
    private static final EmptyCommand INSTANCE = new EmptyCommand();

    private EmptyCommand() {}

    public static EmptyCommand getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        return ConfigManager.getInstance().getProperty(ConfigManager.HOME_PATH);
    }

    @Override
    public String toString() {
        return "EmptyCommand";
    }
}
