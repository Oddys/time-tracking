package org.oddys.timetracking.service;

import org.oddys.timetracking.dto.RoleDto;

import java.util.List;

/**
 * This interface provides functionality related to Roles.
 */
public interface RoleService {
    /**
     * Retrieves all the avilable roles.
     *
     * @return a list of RoleDto objects
     */
    List<RoleDto> findAllRoles();
}
