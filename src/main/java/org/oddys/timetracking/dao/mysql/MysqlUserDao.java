package org.oddys.timetracking.dao.mysql;

import org.oddys.timetracking.dao.UserDao;
import org.oddys.timetracking.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MysqlUserDao implements UserDao {
    private final Connection CONNECTION;
    private static final String FIND_BY_LOGIN = "SELECT * FROM users WHERE login = ?";

    public MysqlUserDao(Connection connection) {
        this.CONNECTION = connection;
    }

    public User findByLogin(String login) {
        try (PreparedStatement statement = CONNECTION.prepareStatement(FIND_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password").toCharArray(),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("role_id")
                );
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException("Failed to find User by login", e);
        }
    }

    @Override
    public Integer create(User entity) {
        return null;
    }

    @Override
    public User findById(Integer id) {
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
    public boolean delete(Integer id) {
        return false;
    }
}
