package org.oddys.timetracking.dao.mysql;

import org.oddys.timetracking.connection.ConnectionWrapper;
import org.oddys.timetracking.dao.ActivityRecordDao;
import org.oddys.timetracking.entity.ActivityRecord;
import org.oddys.timetracking.util.ConfigManager;
import org.oddys.timetracking.util.EntityMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MysqlActivityRecordDao implements ActivityRecordDao {
    private static final ActivityRecordDao INSTANCE = new MysqlActivityRecordDao();

    private MysqlActivityRecordDao() {}

    public static ActivityRecordDao getInstance() {
        return INSTANCE;
    }

    @Override
    public long getNumberOfRows(Long userActivityId) throws DaoException {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty("sql.activity.record.count.rows"))) {
            statement.setLong(1, userActivityId);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getLong("num_rows");
        } catch (SQLException e) {
            throw new DaoException("Failed to get the number of ActivityRecords", e);
        }
    }

    @Override
    public List<ActivityRecord> findAllByUserActivityId(long userActivityId, long currentPage, int recordsPerPage) throws DaoException {
        List<ActivityRecord> records = new ArrayList<>();
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance()
                             .getProperty("sql.activity.record.find.all.page"))) {
            statement.setLong(1, userActivityId);
            statement.setLong(2, (currentPage - 1) * recordsPerPage);
            statement.setInt(3, recordsPerPage);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                records.add(EntityMapper.getInstance().mapActivityRecord(rs));
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to retrieve a page of records", e);
        }
        return records;
    }

    @Override
    public boolean exists(LocalDate date, Long userActivityId)
            throws DaoException {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty("sql.activity.record.check"))) {
//            statement.setDate(1, date);
            statement.setObject(1, date);
            statement.setLong(2, userActivityId);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getLong("count") > 0;
        } catch (SQLException e) {
            throw new DaoException("Failed to check existence of ActivityRecord", e);
        }
    }

    @Override
    public int add(LocalDate date, Long duration, Long userActivityId) throws DaoException {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty("sql.activity.record.add"))) {
//            statement.setDate(1, date);
            statement.setObject(1, date);
            statement.setLong(2, duration);
            statement.setLong(3, userActivityId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Failed to add ActivityRecord", e);
        }
    }
}
