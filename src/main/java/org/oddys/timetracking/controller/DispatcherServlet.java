package org.oddys.timetracking.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.command.Command;
import org.oddys.timetracking.command.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {
                "/controller"
        }
)
public class DispatcherServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger();

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
        Command command = CommandFactory.COMMAND_FACTORY
                .getCommand(req.getParameter("command"));
        String page = command.execute(req);
        if (page == null) {
            log.info("Redirecting to the error page");
            resp.sendError(500);
            return;
        }
        log.info("Forwarding to " + page);
        req.getRequestDispatcher(page).forward(req, resp);
    }
}
