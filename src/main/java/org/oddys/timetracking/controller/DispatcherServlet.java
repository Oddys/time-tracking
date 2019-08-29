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
    private static Logger log = LogManager.getLogger();

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//        process(req, resp);
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String page = null;
        String commandName = req.getParameter("command");
        log.info("Command name provided in the request: " + commandName);
        Command command = CommandFactory.COMMAND_FACTORY.getCommand(commandName);
        try {
            page = command.execute(req);
        } catch (Exception e) {

        }
        log.info("Page obtained by execution of the command: " +  page);

        if (page != null) {
//            req.getRequestDispatcher(page).forward(req, resp);
            resp.sendRedirect(req.getContextPath() + "/" + page);
        }
    }

//    private void processPRG(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//        if ("change_language".equals(req.getParameter("command"))) {
//            resp.sendRedirect("prg?postResult=success&postURL="
//                                      + req.getParameter("currentURL"));
//        }
//    }
}
