package org.oddys.timetracking.dao;

import org.oddys.timetracking.dao.mysql.DaoException;
import org.oddys.timetracking.entity.User;

import java.util.List;

public interface UserDao {
    User findByLogin(String login) throws DaoException;

    List<User> findByLastName(String lastName) throws DaoException;
}
