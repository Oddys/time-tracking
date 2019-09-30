package org.oddys.timetracking.service;

import org.oddys.timetracking.dto.ActivityRecordDto;

import java.util.List;

public interface ActivityRecordService {
    long getNumberOfPages(Long userActivityId, int rowsPerPage);

    List<ActivityRecordDto> findActivityRecords(long userActivityId, long currentPage,
            int recordPerPage);

    int addActivityRecord(String dateString, String durationString, Long userActivityId);
}
