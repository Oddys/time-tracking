package org.oddys.timetracking.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/prg"}
)
public class PRGServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("success".equals(req.getParameter("postResult"))) {
            req.getRequestDispatcher(req.getParameter("postURL")).forward(req, resp);
        }
    }
}
