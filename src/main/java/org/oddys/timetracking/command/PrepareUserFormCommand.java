package org.oddys.timetracking.command;

import org.oddys.timetracking.dto.RoleDto;
import org.oddys.timetracking.service.RoleService;
import org.oddys.timetracking.service.RoleServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PrepareUserFormCommand implements Command {
    private static final Command INSTANCE = new PrepareUserFormCommand();
    private RoleService roleService = RoleServiceImpl.getInstance();

    public static Command getInstance() {
        return INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest req) {
        List<RoleDto> roles = roleService.findAllRoles();
        req.setAttribute("roles", roles);
        return "/cabinet/user-data";
    }
}
