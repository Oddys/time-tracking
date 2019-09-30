package org.oddys.timetracking.dao;

import org.oddys.timetracking.entity.ActivityRecord;

import java.time.LocalDate;
import java.util.List;

public interface ActivityRecordDao {
    long getNumberOfRows(Long userActivityId);

    List<ActivityRecord> findAllByUserActivityId(long userActivityId,
            long currentPage, int RecordsPerPage);

    boolean exists(LocalDate date, Long userActivityId);

    int add(LocalDate date, Long duration, Long userActivityId);
}
