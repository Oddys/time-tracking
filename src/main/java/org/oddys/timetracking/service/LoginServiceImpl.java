package org.oddys.timetracking.service;

import org.oddys.timetracking.connection.ConnectionPool;
import org.oddys.timetracking.dao.DaoFactoryProvider;
import org.oddys.timetracking.dao.RoleDao;
import org.oddys.timetracking.dao.UserDao;
import org.oddys.timetracking.dto.UserDto;
import org.oddys.timetracking.entity.Role;
import org.oddys.timetracking.entity.User;
import org.oddys.timetracking.util.ConfigManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

public class LoginServiceImpl implements LoginService {
    private static final LoginServiceImpl INSTANCE = new LoginServiceImpl();

    private LoginServiceImpl() {}

    public static LoginServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public UserDto logIn(String login, char[] password) {
        UserDto userDto = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
//            connection.setAutoCommit(false);
            String dbmsName = ConfigManager.getInstance().getProperty(
                    ConfigManager.DBMS);
            UserDao userDao = DaoFactoryProvider.getInstance().getFactory(dbmsName)
                    .getUserDao(connection);
            User user = userDao.findByLogin(login);
            if (checkCredentials(login, password, user)) {
                RoleDao roleDao = DaoFactoryProvider.getInstance().getFactory(dbmsName)
                        .getRoleDao(connection);
                Role role = roleDao.findById(user.getRoleId());
                userDto = new UserDto(user, role);
            }
//            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace(); // FIXME Write a custom exception
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
