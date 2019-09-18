package org.oddys.timetracking.dao;

import org.oddys.timetracking.dao.mysql.DaoException;
import org.oddys.timetracking.entity.Role;

import java.util.List;

public interface RoleDao {
    Long create(Role entity) throws DaoException;

    Role findById(Long id) throws DaoException;

    List<Role> findAll() throws DaoException;

    int update(Role entity) throws DaoException;

    boolean delete(Long id) throws DaoException;
}
