package org.oddys.timetracking.command;

import org.oddys.timetracking.service.UserService;
import org.oddys.timetracking.service.UserServiceImpl;
import org.oddys.timetracking.transaction.TransactionProxy;
import org.oddys.timetracking.util.ConfigManager;
import org.oddys.timetracking.util.EntityMapper;
import org.oddys.timetracking.util.ParameterValidator;
import org.oddys.timetracking.util.RequestParametersEncoder;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class AddUserCommand implements Command {
    private static final Command INSTANCE = new AddUserCommand();
    private ParameterValidator validator = ParameterValidator.getInstance();
    private UserService service = TransactionProxy.getInstance().getProxy(
            UserServiceImpl.getInstance());
    private final RequestParametersEncoder encoder;

    private AddUserCommand() {
        encoder = RequestParametersEncoder.getInstance();
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
//        if (!ParameterValidator.getInstance().validateAddUser(req)) {
//            return ConfigManager.getInstance().getProperty("path.user.data");
//        }
        String path = encoder.encodeQueryParameters(
                "redirect:/time-tracking/cabinet/activity-records",
                Map.of("command", "prepare_user_form"));
        Map<String, Boolean> errors = validator.validateAddUser(req);
        if (!errors.isEmpty()) {
            req.getSession().setAttribute("errors", errors);
            return path;
        }
        if (service.addUser(EntityMapper.getInstance().mapUser(req))) {
            req.getSession().setAttribute("messageKey", "User added successfully");
        } else {
            req.getSession().setAttribute("messageKey", "User already exists");
        }
//        return ConfigManager.getInstance().getProperty("path.user.data");
        return path;
    }
}
