package org.oddys.timetracking.dao.mysql;

import org.oddys.timetracking.connection.ConnectionWrapper;
import org.oddys.timetracking.dao.UserActivityDao;
import org.oddys.timetracking.entity.UserActivity;
import org.oddys.timetracking.util.ConfigManager;
import org.oddys.timetracking.util.EntityMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public List<UserActivity> findAllStatusChangeRequested(long currentPage, int rowsPerPage)
            throws DaoException {
        List<UserActivity> userActivities = new ArrayList<>();
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty("sql.user.activity.find.all.requested"))) {
            statement.setLong(1, (currentPage - 1) * rowsPerPage);
            statement.setInt(2, rowsPerPage);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                userActivities.add(EntityMapper.getInstance().mapUserActivity(rs));
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to find all with status change requested", e);
        }
        return userActivities;
    }

    @Override
    public long getNumberOfStatusChangeRequested() throws DaoException {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             Statement statement = connectionWrapper.createStatement()) {
            ResultSet rs = statement.executeQuery(ConfigManager.getInstance()
                    .getProperty("sql.user.activity.requested.count"));
            rs.next();
            return rs.getLong("count");
        } catch (SQLException e) {
            throw new DaoException("Failed to count UserActivities with status change requested", e);
        }
    }

    @Override
    public int updateAssignedAndStatusChangeRequested(Long userActivityId,
            boolean assigned) throws DaoException {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty("sql.user.activity.request.assigned"))) {
            statement.setBoolean(1, assigned);
            statement.setLong(2, userActivityId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to update assigned and status change requested", e);
        }
    }

    @Override
    public UserActivity find(Long userId, Long activityId) throws DaoException {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty("sql.user.activity.find"))) {
            statement.setLong(1, userId);
            statement.setLong(2, activityId);
            ResultSet rs = statement.executeQuery();
            return (rs.next()) ? EntityMapper.getInstance().mapUserActivity(rs) : null;
        } catch (SQLException e) {
            throw new DaoException("Failed to find UserActivity", e);
        }
    }

    @Override
    public int update(UserActivity userActivity) throws DaoException {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty("sql.user.activity.update"))) {
            statement.setBoolean(1, userActivity.getAssigned());
            statement.setBoolean(2, userActivity.getStatusChangeRequested());
            statement.setLong(3, userActivity.getUser().getId());
            statement.setLong(4, userActivity.getActivity().getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to find UserActivity", e);
        }
    }
}
