package org.oddys.timetracking.service;

import org.oddys.timetracking.dto.ActivityRecordDto;

import java.util.List;

public interface ActivityRecordService {
    long getNumberOfPages(Long userActivityId, int rowsPerPage) throws ServiceException;

    List<ActivityRecordDto> findActivityRecords(long userActivityId, long currentPage,
            int recordPerPage) throws ServiceException;

    int addActivityRecord(String dateString, String durationString, Long userActivityId) throws ServiceException;
}
