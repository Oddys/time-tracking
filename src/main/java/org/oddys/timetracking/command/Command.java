package org.oddys.timetracking.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    /**
     * Error code string representing a bad request (http code 400)
     */
    String SC_BAD_REQUEST = "SC=" + HttpServletResponse.SC_BAD_REQUEST;

    /**
     * Processes an HttpServletRequest.
     *
     * @param req HttpServletRequest to be processed
     * @return a String containing the information about the results of execution
     * of this Command, which can be: either 1) a path (with "redirect:" prefix
     * to signal redirecting or with no prefix that means forwarding)
     * or 2) an error code string
     */
    String execute(HttpServletRequest req);
}
