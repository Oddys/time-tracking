package org.oddys.timetracking.service;

import org.oddys.timetracking.dto.UserDto;

public interface LoginService {
    UserDto logIn(String login, char[] password);
}
