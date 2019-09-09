package org.oddys.timetracking.service;

import org.oddys.timetracking.dao.DaoFactoryProvider;
import org.oddys.timetracking.dao.UserDao;
import org.oddys.timetracking.dao.mysql.DaoException;
import org.oddys.timetracking.dto.UserDto;
import org.oddys.timetracking.entity.User;
import org.oddys.timetracking.util.ConfigManager;
import org.oddys.timetracking.util.ModelMapperWrapper;

import java.util.Arrays;
import java.util.Objects;

public class LoginServiceImpl implements LoginService {
    private static final LoginServiceImpl INSTANCE = new LoginServiceImpl();
    private UserDao userDao;

    private LoginServiceImpl() {
        String dbmsName = ConfigManager.getInstance().getProperty(ConfigManager.DBMS);
        userDao = DaoFactoryProvider.getInstance().getFactory(dbmsName).getUserDao();
    }

    public static LoginServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public UserDto logIn(String login, char[] password) throws ServiceException {
        UserDto userDto = null;
        try {
            User user = userDao.findByLogin(login);
            if (checkCredentials(login, password, user)) {
                userDto = ModelMapperWrapper.getMapper().map(user, UserDto.class);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return userDto;
    }

    private boolean checkCredentials(String login, char[] password, User user) {
        if (user == null) {
            return false;
        }
        return Objects.equals(login, user.getLogin())
                && Arrays.equals(password, user.getPassword());
    }
}
