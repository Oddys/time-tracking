package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ChangeLangCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Command INSTANCE = new ChangeLangCommand();
    private final String PREFIX = "/WEB-INF/pages";
    private final String SUFFIX = ".jsp";

    private ChangeLangCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String langParam = req.getParameter("lang");
        String page = req.getParameter("sentFromPage");
        LOGGER.debug("Sent from page: " + page);
        if (langParam != null && page != null) {
            req.getSession().setAttribute("lang", langParam);
            page = page.startsWith("/index.jsp")
                    ? ""
                    : page.replace(PREFIX, "").replace(SUFFIX, "");
            return "redirect:/time-tracking" + page;
        } else {
            return "redirect:" + req.getContextPath();
        }
    }
}
