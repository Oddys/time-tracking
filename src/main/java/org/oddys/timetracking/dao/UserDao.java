package org.oddys.timetracking.dao;

import org.oddys.timetracking.entity.User;

import java.util.Optional;

public interface UserDao extends Dao<Integer, User> {
    Optional<User> findByLogin(String login);
}
