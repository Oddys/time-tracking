package org.oddys.timetracking.dao.mysql;

import org.oddys.timetracking.dao.RoleDao;
import org.oddys.timetracking.entity.Role;

import java.util.List;

public class MysqlRoleDao implements RoleDao {
    MysqlRoleDao() {
    }

    @Override
    public Integer create(Role entity) {
        return null;
    }

    @Override
    public Role findById(Integer id) {
        return null;
    }

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public boolean update(Role entity) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
