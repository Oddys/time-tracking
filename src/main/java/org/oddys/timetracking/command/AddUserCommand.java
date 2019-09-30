package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.service.UserService;
import org.oddys.timetracking.service.UserServiceImpl;
import org.oddys.timetracking.transaction.TransactionProxy;
import org.oddys.timetracking.util.ConfigManager;
import org.oddys.timetracking.util.EntityMapper;
import org.oddys.timetracking.util.ParameterValidator;

import javax.servlet.http.HttpServletRequest;

public class AddUserCommand implements Command {
    private static final Command INSTANCE = new AddUserCommand();
    private UserService service = TransactionProxy.getInstance().getProxy(
            UserServiceImpl.getInstance());

    private AddUserCommand() {}

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        if (!ParameterValidator.getInstance().isValidAddUser(req)) {
            return ConfigManager.getInstance().getProperty("path.user.data");
        }
        if (service.addUser(EntityMapper.getInstance().mapUser(req))) {
            req.setAttribute("message", "User added successfully");
        } else {
            req.setAttribute("message", "User already exists");
        }
        return ConfigManager.getInstance().getProperty("path.user.data");
    }
}
