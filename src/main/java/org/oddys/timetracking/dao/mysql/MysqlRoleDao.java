package org.oddys.timetracking.dao.mysql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.dao.DaoException;
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
    private static final Logger LOGGER = LogManager.getLogger();
    private static final MysqlRoleDao INSTANCE = new MysqlRoleDao();
    private static final String CREATE = "INSERT INTO roles (name) VALUES (?)";
    private static final String FIND_BY_ID = "SELECT * FROM roles WHERE role_id = ?";
    private static final String FIND_ALL = "SELECT * FROM roles ORDER BY role_name";
    private static final String UPDATE = "UPDATE roles SET name = ? WHERE role_id = ?";
    private static final String DELETE = "DELETE FROM roles WHERE role_id = ?";

    private MysqlRoleDao() {}

    public static MysqlRoleDao getInstance() {
        return INSTANCE;
    }

    @Override
    public Long add(Role role) {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper
                     .prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, role.getName());
            statement.executeUpdate();
            return statement.getGeneratedKeys().getLong(1);
        } catch (SQLException e) {
            LOGGER.error("Failed to add Role", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Role findById(Long id) {
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
            LOGGER.error("Failed to retrieve Role", e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<Role> findAll() {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             Statement statement = connectionWrapper.createStatement()) {
            ResultSet rs = statement.executeQuery(FIND_ALL);
            List<Role> roles = new ArrayList<>();
            while (rs.next()) {
                roles.add(new Role(rs.getLong(1), rs.getString(2)));
            }
            return roles;
        } catch (SQLException e) {
            LOGGER.error("Failed to retrieve Roles", e);
            throw new DaoException(e);
        }
    }

    @Override
    public int update(Role role) {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(UPDATE)) {
            statement.setLong(1, role.getId());
            statement.setString(2, role.getName());
            return statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to update Role", e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Failed to delete Role", e);
            throw new DaoException(e);
        }
    }
}
