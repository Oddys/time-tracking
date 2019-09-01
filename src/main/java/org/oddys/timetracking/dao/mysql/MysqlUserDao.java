package org.oddys.timetracking.dao.mysql;

import org.oddys.timetracking.dao.UserDao;
import org.oddys.timetracking.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MysqlUserDao implements UserDao {
    private final Connection connection;
    private static final String FIND_BY_LOGIN = "SELECT * FROM users WHERE login = ?";

    public MysqlUserDao(Connection connection) {
        this.connection = connection;
    }

    public Optional<User> findByLogin(String login) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password").toCharArray(),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("role_id")
                );
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find User by login", e);
        }
    }

    @Override
    public Integer create(User entity) {
        return null;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
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
