package org.oddys.timetracking.dao;

import org.oddys.timetracking.entity.User;

import java.util.List;

public interface UserDao {
    User findById(Long id);

    User findByLogin(String login);

    List<User> findByLastName(String lastName);

    boolean add(User user);
}
