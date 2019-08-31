package org.oddys.timetracking.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest req);
}
