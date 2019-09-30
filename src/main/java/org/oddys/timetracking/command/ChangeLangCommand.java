package org.oddys.timetracking.command;

import org.oddys.timetracking.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;

public class ChangeLangCommand implements Command {
    private static final ChangeLangCommand INSTANCE = new ChangeLangCommand();

    private ChangeLangCommand() {}

    public static ChangeLangCommand getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String langParam = req.getParameter("lang");
        String page = req.getParameter("sentFromPage");
        if (langParam != null && page != null) {
            req.getSession().setAttribute("lang", langParam);
            return page;
        } else {
            return ConfigManager.getInstance().getProperty("path.home");
        }
    }
}
