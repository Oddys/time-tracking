package org.oddys.timetracking.service;

import org.oddys.timetracking.dto.UserDto;

import java.util.List;

public interface FindUserService {
    List<UserDto> search(String lastName);
}
