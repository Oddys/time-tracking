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
    private UserService service = TransactionProxy.getInstance().getProxy(
            UserServiceImpl.getInstance());
    private final ParameterValidator VALIDATOR = ParameterValidator.getInstance();
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
        Map<String, Boolean> errors = VALIDATOR.validateAddUser(req);
        if (!errors.isEmpty()) {
            req.getSession().setAttribute("errors", errors);
            return path;
        }
        String messageKey = service.addUser(MAPPER.mapUser(req)) ? "user.add.success"
                                                                 : "user.add.fail";
        req.getSession().setAttribute("messageKey", messageKey);
        return path;
    }
}
