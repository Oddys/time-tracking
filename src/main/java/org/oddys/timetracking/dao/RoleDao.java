package org.oddys.timetracking.dao;

import org.oddys.timetracking.entity.Role;

import java.util.List;

public interface RoleDao {
    Long add(Role entity);

    Role findById(Long id);

    List<Role> findAll();

    int update(Role entity);

    boolean delete(Long id);
}
