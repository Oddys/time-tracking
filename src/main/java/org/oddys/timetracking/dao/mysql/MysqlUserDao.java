package org.oddys.timetracking.dao.mysql;

import org.oddys.timetracking.dao.UserDao;
import org.oddys.timetracking.entity.User;
import org.oddys.timetracking.connection.ConnectionWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlUserDao implements UserDao {
    private static final MysqlUserDao INSTANCE = new MysqlUserDao();
    private static final String FIND_BY_LOGIN = "SELECT u.*, r.name role_name FROM users u JOIN roles r on u.role_id = r.id WHERE login = ?";
    private static final String FIND_BY_LAST_NAME = "SELECT * FROM users WHERE last_name = ?";

    private MysqlUserDao() {}

    public static MysqlUserDao getInstance() {
        return INSTANCE;
    }

    public User findByLogin(String login) throws DaoException {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.getConnection()
                     .prepareStatement(FIND_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            User user = null;
            if (rs.next()) {
                user = retrieveUser(rs);
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
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     FIND_BY_LAST_NAME)) {
            statement.setString(1, lastName);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                users.add(retrieveUser(rs));
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

    private User retrieveUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getLong("id"),
                rs.getString("login"),
                rs.getString("password").toCharArray(),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getLong("role_id"),
                rs.getString("role_name")
        );
    }
}
