package org.oddys.timetracking.dao.mysql;

import org.oddys.timetracking.connection.ConnectionWrapper;
import org.oddys.timetracking.dao.ActivityDao;
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
    private static final MysqlActivityDao INSTANCE = new MysqlActivityDao();
//    private static final String CREATE = "INSERT INTO activities (name, approved) VALUES (?, ?)";
//    private static final String FIND_ALL = "SELECT * FROM activities";
//    private static final String UPDATE = "UPDATE activities SET approved = ? WHERE name = ?";

    private MysqlActivityDao() {}

    public static MysqlActivityDao getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean create(String activityName) throws DaoException {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty("sql.activity.add"))) {
            statement.setString(1, activityName);
//            statement.setBoolean(2, activity.isApproved());
            return statement.executeUpdate() > 0;
//            return statement.getGeneratedKeys().getLong(1);
        } catch (SQLException e) {
            throw new DaoException("Failed to create Activity", e);
        }
    }

    @Override
    public List<Activity> findAll(long currentPage, int rowsPerPage) throws DaoException {
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
            throw new DaoException("Failed to retrieve Activities", e);
        }
    }

    @Override
    public long getNumberOfRows() throws DaoException {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             Statement statement = connectionWrapper.createStatement()) {
            ResultSet rs = statement.executeQuery(ConfigManager.getInstance()
                    .getProperty("sql.activity.count.rows"));
            rs.next();
            return rs.getLong("num_activities");
        } catch (SQLException e) {
            throw new DaoException("Failed to get the number of Activities");
        }
    }

    @Override
    public Activity findByName(String activityName) throws DaoException {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty("sql.activity.find.by.name"))) {
            statement.setString(1, activityName);
            ResultSet rs = statement.executeQuery();
            return rs.next() ? EntityMapper.getInstance().mapActivity(rs) : null;
        } catch (SQLException e) {
            throw new DaoException("Failed to find Activity by name", e);
        }
    }

    //    @Override
//    public Activity findById(Long id) {
//        return null;
//    }

//    @Override
//    public List<Activity> findAll() throws DaoException {
//        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
//             Statement statement = connectionWrapper.createStatement()) {
//            ResultSet rs = statement.executeQuery(FIND_ALL);
//            List<Activity> activities = new ArrayList<>();
//            while (rs.next()) {
//                Activity activity = new Activity(rs.getLong(1), rs.getString(2));
////                        rs.getBoolean(3));
//                activities.add(activity);
//            }
//            return activities;
//        } catch (SQLException e) {
//            throw new DaoException("Failed to retrieve Activities", e);
//        }

//    }

//    @Override
//    public int update(Activity activity) throws DaoException {
//        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
//             PreparedStatement statement = connectionWrapper.prepareStatement(UPDATE)) {
//            statement.setBoolean(1, activity.isApproved());
//            statement.setString(1, activity.getName());
//            return statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new DaoException("Failed to update Activity", e);
//        }
//    }
}
