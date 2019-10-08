package org.oddys.timetracking.dao.mysql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.connection.ConnectionWrapper;
import org.oddys.timetracking.dao.DaoException;
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
    private static final Logger LOGGER = LogManager.getLogger();
    private static final MysqlUserActivityDao INSTANCE = new MysqlUserActivityDao();

    private MysqlUserActivityDao() {}

    public static MysqlUserActivityDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<UserActivity> findAllByUserId(Long userId) {
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
            LOGGER.error("Failed to retrieve UserActivities by userId", e);
            throw new DaoException(e);
        }
        return activities;
    }

    @Override
    public int add(Long userId, Long activityId, Boolean assigned,
            Boolean statusChangeRequested) {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty("sql.user.activity.add"))) {
            statement.setLong(1, userId);
            statement.setLong(2, activityId);
            statement.setBoolean(3, assigned);
            statement.setBoolean(4, statusChangeRequested);
            return statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to add UserActivity", e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean exists(Long userId, Long activityId) {
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
            LOGGER.error("Failed to check the existence of UserActivity", e);
            throw new DaoException(e);
        }
    }

    @Override
    public int requestStatusChange(Long userActivityId) {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty("sql.user.activity.request"))) {
            statement.setLong(1, userActivityId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to send a request for the status change of UserActivity", e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<UserActivity> findAllStatusChangeRequested(long currentPage, int rowsPerPage) {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty("sql.user.activity.find.all.requested"))) {
            List<UserActivity> userActivities = new ArrayList<>();
            statement.setLong(1, (currentPage - 1) * rowsPerPage);
            statement.setInt(2, rowsPerPage);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                userActivities.add(EntityMapper.getInstance().mapUserActivity(rs));
            }
            return userActivities;
        } catch (SQLException e) {
            LOGGER.error("Failed to retrieve UserActivities whose status should be changed", e);
            throw new DaoException(e);
        }
    }

    @Override
    public long getNumberOfStatusChangeRequested() {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             Statement statement = connectionWrapper.createStatement()) {
            ResultSet rs = statement.executeQuery(ConfigManager.getInstance()
                    .getProperty("sql.user.activity.requested.count"));
            rs.next();
            return rs.getLong("count");
        } catch (SQLException e) {
            LOGGER.error("Failed to count UserActivities with status change requested", e);
            throw new DaoException(e);
        }
    }

    @Override
    public int updateAssignedAndStatusChangeRequested(Long userActivityId,
            boolean assigned) {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty("sql.user.activity.request.assigned"))) {
            statement.setBoolean(1, assigned);
            statement.setLong(2, userActivityId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to update UserActivity (assigned and statusChangeRequested)", e);
            throw new DaoException(e);
        }
    }

    @Override
    public UserActivity findByUserIdAndActivityId(Long userId, Long activityId) {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty("sql.user.activity.find"))) {
            statement.setLong(1, userId);
            statement.setLong(2, activityId);
            ResultSet rs = statement.executeQuery();
            return (rs.next()) ? EntityMapper.getInstance().mapUserActivity(rs) : null;
        } catch (SQLException e) {
            LOGGER.error("Failed to find UserActivity by userId and activityId", e);
            throw new DaoException(e);
        }
    }

    @Override
    public int update(UserActivity userActivity) {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty("sql.user.activity.update"))) {
            statement.setBoolean(1, userActivity.getAssigned());
            statement.setBoolean(2, userActivity.getStatusChangeRequested());
            statement.setLong(3, userActivity.getUser().getId());
            statement.setLong(4, userActivity.getActivity().getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to update UserActivity", e);
            throw new DaoException(e);
        }
    }

    @Override
    public UserActivity findById(Long id) {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty("sql.user.activity.find.by.id"))) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            return (rs.next()) ? EntityMapper.getInstance().mapUserActivity(rs) : null;
        } catch (SQLException e) {
            LOGGER.error("Failed to find UserActivity by id", e);
            throw new DaoException(e);
        }
    }
}
