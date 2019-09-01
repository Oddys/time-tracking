package org.oddys.timetracking.dao.mysql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.dao.RoleDao;
import org.oddys.timetracking.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MysqlRoleDao implements RoleDao {
//    private static final Logger log = LogManager.getLogger();
    private final Connection connection;
    private static final String CREATE = "INSERT INTO roles (name) VALUES (?)";
    private static final String FIND_BY_ID = "SELECT * FROM roles WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM roles";
    private static final String UPDATE = "UPDATE roles SET name = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM roles WHERE id = ?";

    MysqlRoleDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer create(Role entity) {
        try (PreparedStatement statement = connection
                .prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.executeUpdate();
            return statement.getGeneratedKeys().getInt(1);
        } catch (SQLException e) {
            throw new DaoException("Failed to create Role", e);
        }
    }

    @Override
    public Optional<Role> findById(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(new Role(rs.getInt(1), rs.getString(2)));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to retrieve Role", e);
        }
    }

    @Override
    public List<Role> findAll() {
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(FIND_ALL);
            List<Role> roles = new ArrayList<>();
            while (rs.next()) {
                roles.add(new Role(rs.getInt(1), rs.getString(2)));
            }
            return roles;
        } catch (SQLException e) {
            throw new DaoException("Failed to retrieve Roles");
        }
    }

    @Override
    public boolean update(Role entity) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getName());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Failed to update Role", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Failed to delete Role", e);
        }
    }
}
