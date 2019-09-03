package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;

public class ChangeLangCommand implements Command {
    private static final ChangeLangCommand INSTANCE = new ChangeLangCommand();
    private static final Logger log = LogManager.getLogger();

    private ChangeLangCommand() {}

    public static ChangeLangCommand getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String langParam = req.getParameter("lang");
        if (langParam != null) {
            req.getSession().setAttribute("lang", langParam);
            log.info("Session language is set to " + langParam);
            return req.getParameter("sentFromPage");
        } else {
            return ConfigManager.getInstance().getProperty(ConfigManager.HOME_PATH);
        }
    }

    @Override
    public String toString() {
        return "ChangeLangCommand";
    }
}
