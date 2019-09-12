package org.oddys.timetracking.dao.mysql;

import org.oddys.timetracking.connection.ConnectionWrapper;
import org.oddys.timetracking.dao.ActivityRecordDao;
import org.oddys.timetracking.entity.ActivityRecord;
import org.oddys.timetracking.util.ConfigManager;
import org.oddys.timetracking.util.EntityMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlActivityRecordDao implements ActivityRecordDao {
    private static final ActivityRecordDao INSTANCE = new MysqlActivityRecordDao();
//    private static final String COUNT_ROWS = "SELECT count(*) num_rows FROM activity_records";
//    private static final String FIND_ALL_FOR_PAGE = "SELECT * FROM activity_records LIMIT ?, ?";

    private MysqlActivityRecordDao() {}

    public static ActivityRecordDao getInstance() {
        return INSTANCE;
    }

    @Override
    public long getNumberOfRows() throws DaoException {
        try (ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();
             Statement statement = connectionWrapper.createStatement()) {
            ResultSet rs = statement.executeQuery(
                    ConfigManager.getInstance().getProperty("sql.activity.record.count.rows"));
            rs.next();
            return rs.getLong("num_rows");
        } catch (SQLException e) {
            throw new DaoException("Failed to get the number of rows", e);
        }
    }

    @Override
    public List<ActivityRecord> findActivityRecords(long userActivityId, long currentPage, int recordsPerPage) throws DaoException {
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
}
