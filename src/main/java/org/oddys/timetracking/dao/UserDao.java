package org.oddys.timetracking.dao;

import org.oddys.timetracking.entity.User;

public interface UserDao extends Dao<Integer, User> {
    User findByLogin(String login);
}
