package org.oddys.timetracking.service;

import org.oddys.timetracking.dto.ActivityRecordsPage;

public interface ActivityRecordService {
    long getNumberOfPages(Long userActivityId, int rowsPerPage);

    ActivityRecordsPage findActivityRecords(long userActivityId, long currentPage,
            int recordPerPage);

    int addActivityRecord(String dateString, String durationString, Long userActivityId);
}
