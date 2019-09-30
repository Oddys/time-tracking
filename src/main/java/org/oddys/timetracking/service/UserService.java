package org.oddys.timetracking.service;

import org.oddys.timetracking.dto.UserDto;
import org.oddys.timetracking.entity.User;

import java.util.List;

public interface UserService {
    List<UserDto> search(String lastName);

    boolean addUser(User user);

    UserDto signIn(String login, char[] password);
}
