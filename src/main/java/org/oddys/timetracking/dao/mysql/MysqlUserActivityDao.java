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
    public boolean addUserActivity(UserActivity activity) {
        return false;
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
