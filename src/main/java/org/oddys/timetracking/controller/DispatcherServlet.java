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
        String page = null;

        Command command = CommandFactory.COMMAND_FACTORY.getCommand(
                req.getParameter("command"));
        if (command != null) {
            page = command.execute(req);
        }

        if (page != null) {
            log.info("Forwarding to " + page);
//            resp.sendRedirect(req.getContextPath() + "/" + page);
            req.getRequestDispatcher(page).forward(req, resp);
        } else {  /* Default */
            log.info("Forwarding to the home page");
//            resp.sendRedirect(req.getContextPath());
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
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
