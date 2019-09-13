package org.oddys.timetracking.dao;

import org.oddys.timetracking.dao.mysql.DaoException;
import org.oddys.timetracking.entity.ActivityRecord;

import java.sql.Date;
import java.util.List;

public interface ActivityRecordDao {
    long getNumberOfRows(Long userActivityId) throws DaoException;

    List<ActivityRecord> findAllByUserActivityId(long userActivityId, long currentPage,
            int RecordsPerPage) throws DaoException;

    boolean exists(Date date, Long userActivityId) throws DaoException;

    int add(Date date, Long duration, Long userActivityId) throws DaoException;
}
