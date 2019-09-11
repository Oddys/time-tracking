package org.oddys.timetracking.dao.mysql;

import org.oddys.timetracking.connection.ConnectionWrapper;
import org.oddys.timetracking.dao.UserActivityDao;
import org.oddys.timetracking.entity.Activity;
import org.oddys.timetracking.entity.UserActivity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlUserActivityDao implements UserActivityDao {
    private static final MysqlUserActivityDao INSTANCE = new MysqlUserActivityDao();
    private static final String FIND_ALL_BY_USER_ID = "SELECT ua.*, a.id AS activity_id, "
            + "a.name AS activity_name, a.approved AS activity_approved FROM user_activities ua "
            + "JOIN activities a ON ua.activity_id = a.id WHERE user_id = ?";

    private MysqlUserActivityDao() {}

    public static MysqlUserActivityDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<UserActivity> findAllByUserId(Long userId) throws DaoException {
        List<UserActivity> activities = new ArrayList<>();
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement preparedStatement = connectionWrapper.prepareStatement(FIND_ALL_BY_USER_ID)) {
            preparedStatement.setLong(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                UserActivity userActivity = new UserActivity();
                userActivity.setId(rs.getLong("id"));
                userActivity.setAssigned(rs.getBoolean("assigned"));
                userActivity.setActivity(new Activity(rs.getLong("activity_id"),
                        rs.getString("activity_name"),
                        rs.getBoolean("activity_approved")));
                activities.add(userActivity);
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find all by User's ID", e);
        }
        return activities;
    }

    @Override
    public Long create(UserActivity entity) throws DaoException {
        return null;
    }

    @Override
    public UserActivity findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public List<UserActivity> findAll() throws DaoException {
        return null;
    }

    @Override
    public boolean update(UserActivity entity) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        return false;
    }
}
