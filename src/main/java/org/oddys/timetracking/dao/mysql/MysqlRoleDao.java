package org.oddys.timetracking.dao.mysql;

import org.oddys.timetracking.dao.RoleDao;
import org.oddys.timetracking.entity.Role;
import org.oddys.timetracking.connection.ConnectionWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlRoleDao implements RoleDao {
    private static final MysqlRoleDao INSTANCE = new MysqlRoleDao();
    private static final String CREATE = "INSERT INTO roles (name) VALUES (?)";
    private static final String FIND_BY_ID = "SELECT * FROM roles WHERE role_id = ?";
    private static final String FIND_ALL = "SELECT * FROM roles";
    private static final String UPDATE = "UPDATE roles SET name = ? WHERE role_id = ?";
    private static final String DELETE = "DELETE FROM roles WHERE role_id = ?";

    private MysqlRoleDao() {}

    public static MysqlRoleDao getInstance() {
        return INSTANCE;
    }

    @Override
    public Long create(Role entity) throws DaoException {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper
                     .prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.executeUpdate();
            return statement.getGeneratedKeys().getLong(1);
        } catch (SQLException e) {
            throw new DaoException("Failed to create Role", e);
        }
    }

    @Override
    public Role findById(Long id) throws DaoException {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            Role role = null;
            if (rs.next()) {
                role = new Role(rs.getLong(1), rs.getString(2));
            }
            return role;
        } catch (SQLException e) {
            throw new DaoException("Failed to retrieve Role", e);
        }
    }

    @Override
    public List<Role> findAll() throws DaoException {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             Statement statement = connectionWrapper.createStatement()) {
            ResultSet rs = statement.executeQuery(FIND_ALL);
            List<Role> roles = new ArrayList<>();
            while (rs.next()) {
                roles.add(new Role(rs.getLong(1), rs.getString(2)));
            }
            return roles;
        } catch (SQLException e) {
            throw new DaoException("Failed to retrieve Roles");
        }
    }

    @Override
    public boolean update(Role entity) throws DaoException {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(UPDATE)) {
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getName());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Failed to update Role", e);
        }
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Failed to delete Role", e);
        }
    }
}
