package org.oddys.timetracking.dao.mysql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.dao.DaoException;
import org.oddys.timetracking.dao.UserDao;
import org.oddys.timetracking.entity.User;
import org.oddys.timetracking.connection.ConnectionWrapper;
import org.oddys.timetracking.util.ConfigManager;
import org.oddys.timetracking.util.EntityMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlUserDao implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final MysqlUserDao INSTANCE = new MysqlUserDao();
    private static final String FIND_BY_LOGIN = "SELECT u.*, r.role_name FROM users u JOIN roles r on u.role_id = r.role_id WHERE u.login = ?";
    private static final String FIND_BY_LAST_NAME = "SELECT u.*, r.role_name FROM users u JOIN roles r on u.role_id = r.role_id WHERE u.last_name = ?";
    private static final String FIND_BY_ID = "SELECT u.*, r.role_name FROM users u JOIN roles r on u.role_id = r.role_id WHERE u.user_id = ?";

    private MysqlUserDao() {}

    public static MysqlUserDao getInstance() {
        return INSTANCE;
    }

    public User findByLogin(String login) {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(FIND_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            return (rs.next()) ? EntityMapper.getInstance().mapUser(rs) : null;
        } catch (SQLException e) {
            LOGGER.error("Failed to find User by login", e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> findByLastName(String lastName) {
        List<User> users = new ArrayList<>();
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper
                     .prepareStatement(FIND_BY_LAST_NAME)) {
            statement.setString(1, lastName);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                users.add(EntityMapper.getInstance().mapUser(rs));
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to find User by last name", e);
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public boolean add(User user) {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty("sql.user.add"))) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getRole().getName());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Failed to add User", e);
            throw new DaoException(e);
        }
    }

    @Override
    public User findById(Long id) {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            return (rs.next()) ? EntityMapper.getInstance().mapUser(rs) : null;
        } catch (SQLException e) {
            LOGGER.error("Failed to find User by id", e);
            throw new DaoException(e);
        }
    }
}
