package org.oddys.timetracking.service;

import org.oddys.timetracking.connection.ConnectionPool;
import org.oddys.timetracking.dao.DaoFactoryProvider;
import org.oddys.timetracking.dao.RoleDao;
import org.oddys.timetracking.dao.UserDao;
import org.oddys.timetracking.dto.UserDto;
import org.oddys.timetracking.entity.Role;
import org.oddys.timetracking.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

public class LoginService {
    private static final LoginService INSTANCE = new LoginService();

    private LoginService() {}

    public static LoginService getInstance() {
        return INSTANCE;
    }

    public UserDto logIn(String login, char[] password) {
        UserDto userDto = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
//            connection.setAutoCommit(false);
            UserDao userDao = DaoFactoryProvider.getInstance()
                 .getFactory().getUserDao(connection);
            User user = userDao.findByLogin(login);
            if (checkCredentials(login, password, user)) {
                RoleDao roleDao = DaoFactoryProvider.getInstance()
                                                    .getFactory().getRoleDao(connection);
                Role role = roleDao.findById(user.getRoleId());
                userDto = new UserDto(user, role);
            }
//            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace(); // TODO Write a custom exception
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
