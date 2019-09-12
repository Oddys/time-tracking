package org.oddys.timetracking.dao;

import org.oddys.timetracking.dao.mysql.DaoException;
import org.oddys.timetracking.entity.ActivityRecord;

import java.util.List;

public interface ActivityRecordDao {
    int getNumberOfRows() throws DaoException;

    List<ActivityRecord> findActivityRecords(int currentPage, int RecordsPerPage) throws DaoException;
}
