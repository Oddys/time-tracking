package org.oddys.timetracking.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/static/*")
public class StaticContentServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getNamedDispatcher("default");
        HttpServletRequest wrapped = new HttpServletRequestWrapper(req) {
            public String getServletPath() {
                return "/static/";
            }
        };
        rd.forward(wrapped, resp);
    }
}
