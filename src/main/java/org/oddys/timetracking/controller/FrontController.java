package org.oddys.timetracking.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.command.Command;
import org.oddys.timetracking.command.CommandFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/")
public class FrontController extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();
    private final String PREFIX = "/WEB-INF/pages";
    private final String SUFFIX = ".jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getServletPath().startsWith("/static")) {
            RequestDispatcher rd = getServletContext().getNamedDispatcher("default");
            rd.forward(req, resp);
            return;
        }
        LOGGER.debug("Controller path: " + req.getRequestURI());
        Command command = CommandFactory.COMMAND_FACTORY.getCommand(
                req.getParameter("command"));
        String viewName = command.execute(req);
        if (viewName.startsWith("SC=")) {
            req.setAttribute("javax.servlet.error.status_code", getErrorCode(viewName));
            req.getRequestDispatcher(resolveView("/error")).forward(req, resp);
        } else if (viewName.startsWith("redirect:")) {
            LOGGER.debug("View name: " + viewName);
            resp.sendRedirect(viewName.replace("redirect:", ""));
        } else {
            String viewPath = resolveView(viewName);
            LOGGER.debug("View path: " + viewPath);
            req.getRequestDispatcher(viewPath).forward(req, resp);
        }
    }

    private String resolveView(String page) {
        String prefix = "/error".equals(page) ? "/WEB-INF" : PREFIX;
        return  prefix + page + SUFFIX;
    }

    private int getErrorCode(String s) {
        Matcher m = Pattern.compile("SC=(\\d+)").matcher(s);
        if (m.matches()) {
            return Integer.parseInt(m.group(1));
        } else {
            return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        }
    }
}
