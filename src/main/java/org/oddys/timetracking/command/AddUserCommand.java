package org.oddys.timetracking.command;

import org.oddys.timetracking.service.UserService;
import org.oddys.timetracking.service.UserServiceImpl;
import org.oddys.timetracking.transaction.TransactionProxy;
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
    private final RequestParametersEncoder ENCODER;
    private final EntityMapper MAPPER;

    private AddUserCommand() {
        ENCODER = RequestParametersEncoder.getInstance();
        MAPPER = EntityMapper.getInstance();
    }

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String path = ENCODER.encodeQueryParameters(
                "redirect:/time-tracking/cabinet/user-data",
                Map.of("command", "prepare_user_form"));
        Map<String, Boolean> errors = validator.validateAddUser(req);
        if (!errors.isEmpty()) {
            req.getSession().setAttribute("errors", errors);
            return path;
        }
        if (service.addUser(MAPPER.mapUser(req))) {
            req.getSession().setAttribute("messageKey", "User added successfully");
        } else {
            req.getSession().setAttribute("messageKey", "User already exists");
        }
        return path;
    }
}
