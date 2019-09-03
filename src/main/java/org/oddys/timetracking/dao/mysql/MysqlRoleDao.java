package org.oddys.timetracking.dao.mysql;

import org.oddys.timetracking.dao.RoleDao;
import org.oddys.timetracking.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlRoleDao implements RoleDao {
//    private static final Logger log = LogManager.getLogger();
    private final Connection CONNECTION;
    private static final String CREATE = "INSERT INTO roles (name) VALUES (?)";
    private static final String FIND_BY_ID = "SELECT * FROM roles WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM roles";
    private static final String UPDATE = "UPDATE roles SET name = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM roles WHERE id = ?";

    MysqlRoleDao(Connection connection) {
        this.CONNECTION = connection;
    }

    @Override
    public Integer create(Role entity) {
        try (PreparedStatement statement = CONNECTION
                .prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.executeUpdate();
            return statement.getGeneratedKeys().getInt(1);
        } catch (SQLException e) {
            throw new DaoException("Failed to create Role", e);
        }
    }

    @Override
    public Role findById(Integer id) {
        try (PreparedStatement statement = CONNECTION.prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            Role role = null;
            if (rs.next()) {
                role = new Role(rs.getInt(1), rs.getString(2));
            }
            return role;
        } catch (SQLException e) {
            throw new DaoException("Failed to retrieve Role", e);
        }
    }

    @Override
    public List<Role> findAll() {
        try (Statement statement = CONNECTION.createStatement()) {
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
        try (PreparedStatement statement = CONNECTION.prepareStatement(UPDATE)) {
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getName());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Failed to update Role", e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        try (PreparedStatement statement = CONNECTION.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Failed to delete Role", e);
        }
    }
}
