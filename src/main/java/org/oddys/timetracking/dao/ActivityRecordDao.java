package org.oddys.timetracking.dao;

import org.oddys.timetracking.dao.mysql.DaoException;
import org.oddys.timetracking.entity.ActivityRecord;

import java.util.List;

public interface ActivityRecordDao {
    long getNumberOfRows() throws DaoException;

    List<ActivityRecord> findActivityRecords(long userActivityId, long currentPage, int RecordsPerPage) throws DaoException;
}
