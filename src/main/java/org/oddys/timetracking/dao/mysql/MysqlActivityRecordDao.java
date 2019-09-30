package org.oddys.timetracking.dao.mysql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.connection.ConnectionWrapper;
import org.oddys.timetracking.dao.ActivityRecordDao;
import org.oddys.timetracking.dao.DaoException;
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
    private static final Logger LOGGER = LogManager.getLogger();
    private static final ActivityRecordDao INSTANCE = new MysqlActivityRecordDao();

    private MysqlActivityRecordDao() {}

    public static ActivityRecordDao getInstance() {
        return INSTANCE;
    }

    @Override
    public long getNumberOfRows(Long userActivityId) {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty("sql.activity.record.count.rows"))) {
            statement.setLong(1, userActivityId);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getLong("num_rows");
        } catch (SQLException e) {
            LOGGER.error("Failed to get the total of ActivityRecords", e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<ActivityRecord> findAllByUserActivityId(long userActivityId,
            long currentPage, int recordsPerPage) {
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
            LOGGER.error("Failed to retrieve a page of ActivityRecords", e);
            throw new DaoException(e);
        }
        return records;
    }

    @Override
    public boolean exists(LocalDate date, Long userActivityId) {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty("sql.activity.record.check"))) {
            statement.setObject(1, date);
            statement.setLong(2, userActivityId);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getLong("count") > 0;
        } catch (SQLException e) {
            LOGGER.error("Failed to check the existence of ActivityRecord", e);
            throw new DaoException(e);
        }
    }

    @Override
    public int add(LocalDate date, Long duration, Long userActivityId) {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             PreparedStatement statement = connectionWrapper.prepareStatement(
                     ConfigManager.getInstance().getProperty("sql.activity.record.add"))) {
            statement.setObject(1, date);
            statement.setLong(2, duration);
            statement.setLong(3, userActivityId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed to add ActivityRecord", e);
            throw new DaoException(e);
        }
    }
}
