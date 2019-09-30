package org.oddys.timetracking.dao.mysql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.connection.ConnectionWrapper;
import org.oddys.timetracking.dao.ActivityDao;
import org.oddys.timetracking.dao.DaoException;
import org.oddys.timetracking.entity.Activity;
import org.oddys.timetracking.util.ConfigManager;
import org.oddys.timetracking.util.EntityMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlActivityDao implements ActivityDao {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final MysqlActivityDao INSTANCE = new MysqlActivityDao();

    private MysqlActivityDao() {}

    public static MysqlActivityDao getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(String activityName) {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty("sql.activity.add"))) {
            statement.setString(1, activityName);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Failed to add Activity", e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<Activity> findAll(long currentPage, int rowsPerPage) {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty("sql.activity.find.all.page"))) {
            statement.setLong(1, (currentPage - 1) * rowsPerPage);
            statement.setLong(2, rowsPerPage);
            ResultSet rs = statement.executeQuery();
            List<Activity> activities = new ArrayList<>();
            while (rs.next()) {
                activities.add(EntityMapper.getInstance().mapActivity(rs));
            }
            return activities;
        } catch (SQLException e) {
            LOGGER.error("Failed to retrieve a page of Activities", e);
            throw new DaoException(e);
        }
    }

    @Override
    public long getNumberOfRows() {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             Statement statement = connectionWrapper.createStatement()) {
            ResultSet rs = statement.executeQuery(ConfigManager.getInstance()
                    .getProperty("sql.activity.count.rows"));
            rs.next();
            return rs.getLong("num_activities");
        } catch (SQLException e) {
            LOGGER.error("Failed to get the total of Activities", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Activity findByName(String activityName) {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty("sql.activity.find.by.name"))) {
            statement.setString(1, activityName);
            ResultSet rs = statement.executeQuery();
            return rs.next() ? EntityMapper.getInstance().mapActivity(rs) : null;
        } catch (SQLException e) {
            LOGGER.error("Failed to find Activity by name", e);
            throw new DaoException(e);
        }
    }
}
