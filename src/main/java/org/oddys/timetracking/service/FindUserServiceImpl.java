package org.oddys.timetracking.service;

import org.oddys.timetracking.dao.DaoFactoryProvider;
import org.oddys.timetracking.dao.UserDao;
import org.oddys.timetracking.dto.UserDto;
import org.oddys.timetracking.util.ConfigManager;

import java.util.List;

public class FindUserServiceImpl implements FindUserService {
    private UserDao userDao = DaoFactoryProvider.getInstance().getFactory(
            ConfigManager.DBMS).getUserDao();

    @Override
    public List<UserDto> search(String lastName) {
        return null;
    }
}
