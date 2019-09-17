package org.oddys.timetracking.dao.mysql;

import org.oddys.timetracking.connection.ConnectionWrapper;
import org.oddys.timetracking.dao.UserActivityDao;
import org.oddys.timetracking.entity.UserActivity;
import org.oddys.timetracking.util.ConfigManager;
import org.oddys.timetracking.util.EntityMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlUserActivityDao implements UserActivityDao {
    private static final MysqlUserActivityDao INSTANCE = new MysqlUserActivityDao();

    private MysqlUserActivityDao() {}

    public static MysqlUserActivityDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<UserActivity> findAllByUserId(Long userId) throws DaoException {
        List<UserActivity> activities = new ArrayList<>();
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(ConfigManager.getInstance()
                     .getProperty("sql.user.activity.find.all.by.user.id"))) {
            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                activities.add(EntityMapper.getInstance().mapUserActivity(rs));
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find all by User's ID", e);
        }
        return activities;
    }

    @Override
    public int add(Long userId, Long activityId, Boolean assigned,
            Boolean statusChangeRequested) throws DaoException {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty("sql.user.activity.add"))) {
            statement.setLong(1, userId);
            statement.setLong(2, activityId);
            statement.setBoolean(3, assigned);
            statement.setBoolean(4, statusChangeRequested);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to add UserActivity", e);
        }
    }

    @Override
    public boolean exists(Long userId, Long activityId) throws DaoException {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty(
                             "sql.user.activity.check"))) {
            statement.setLong(1, userId);
            statement.setLong(2, activityId);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getLong("count") > 0;
        } catch (SQLException e) {
            throw new DaoException("Failed to find UserActivity", e);
        }
    }

    @Override
    public int requestStatusChange(Long userActivityId) throws DaoException {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty("sql.user.activity.request"))) {
            statement.setLong(1, userActivityId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to request status change", e);
        }
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
