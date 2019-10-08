package org.oddys.timetracking.service;

import org.oddys.timetracking.dto.RoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> findAllRoles();
}
