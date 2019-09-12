package org.oddys.timetracking.dao.mysql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.dao.UserDao;
import org.oddys.timetracking.entity.User;
import org.oddys.timetracking.connection.ConnectionWrapper;
import org.oddys.timetracking.util.EntityMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlUserDao implements UserDao {
    private static final Logger log = LogManager.getLogger();
    private static final MysqlUserDao INSTANCE = new MysqlUserDao();
    private static final String FIND_BY_LOGIN = "SELECT u.*, r.role_name FROM users u JOIN roles r on u.role_id = r.role_id WHERE u.login = ?";
    private static final String FIND_BY_LAST_NAME = "SELECT u.*, r.role_name FROM users u JOIN roles r on u.role_id = r.role_id WHERE u.last_name = ?";

    private MysqlUserDao() {}

    public static MysqlUserDao getInstance() {
        return INSTANCE;
    }

    public User findByLogin(String login) throws DaoException {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(FIND_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            User user = null;
            if (rs.next()) {
                user = EntityMapper.getInstance().mapUser(rs);
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException("Failed to find User by login", e);
        }
    }

    @Override
    public List<User> findByLastName(String lastName) throws DaoException {
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
            throw new DaoException("Failed to find User by last name", e);
        }
        return users;
    }

    @Override
    public Long create(User entity) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
