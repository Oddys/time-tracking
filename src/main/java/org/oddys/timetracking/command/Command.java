package org.oddys.timetracking.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    String SC_BAD_REQUEST = "SC=" + HttpServletResponse.SC_BAD_REQUEST;

    String execute(HttpServletRequest req);
}
