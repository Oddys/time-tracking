package org.oddys.timetracking.service;

import org.oddys.timetracking.dto.ActivityRecordsPage;

import java.time.LocalDate;

public interface ActivityRecordService {
    long getNumberOfPages(Long userActivityId, int rowsPerPage);

    ActivityRecordsPage findActivityRecords(long userActivityId, long currentPage,
            int recordPerPage);

    boolean addActivityRecord(LocalDate date, long duration, long userActivityId);
}
