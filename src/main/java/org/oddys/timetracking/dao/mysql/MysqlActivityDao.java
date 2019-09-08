package org.oddys.timetracking.dao.mysql;

import org.oddys.timetracking.dao.ActivityDao;
import org.oddys.timetracking.entity.Activity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlActivityDao implements ActivityDao {
    private final Connection CONNECTION;
    private static final String CREATE = "INSERT INTO activities (name, approved) VALUES (?, ?)";
    private static final String FIND_ALL = "SELECT * FROM activities";
    private static final String UPDATE = "UPDATE activities SET approved = ? WHERE name = ?";

    MysqlActivityDao(Connection connection) {
        CONNECTION = connection;
    }

    @Override
    public Long create(Activity entity) {
        try (PreparedStatement statement = CONNECTION.prepareStatement(CREATE,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.setBoolean(2, entity.isApproved());
            statement.executeUpdate();
            return statement.getGeneratedKeys().getLong(1);
        } catch (SQLException e) {
            throw new DaoException("Failed to create Activity", e);
        }
    }

    @Override
    public Activity findById(Long id) {
        return null;
    }

    @Override
    public List<Activity> findAll() {
        try (Statement statement = CONNECTION.createStatement()) {
            ResultSet rs = statement.executeQuery(FIND_ALL);
            List<Activity> activities = new ArrayList<>();
            while (rs.next()) {
                Activity activity = new Activity(rs.getLong(1), rs.getString(2),
                        rs.getBoolean(3));
                activities.add(activity);
            }
            return activities;
        } catch (SQLException e) {
            throw new DaoException("Failed to retrieve Activities", e);
        }
    }

    @Override
    public boolean update(Activity entity) {
        try (PreparedStatement statement = CONNECTION.prepareStatement(UPDATE)) {
            statement.setBoolean(1, entity.isApproved());
            statement.setString(2, entity.getName());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Failed to update Activity", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
