package org.oddys.timetracking.service;

import org.oddys.timetracking.dto.ActivityRecordDto;

import java.util.List;

public interface ActivityRecordService {
    long getNumberOfPages(int rowsPerPage) throws ServiceException;

    List<ActivityRecordDto> findActivityRecords(long userActivityId, long currentPage,
            int recordPerPage) throws ServiceException;
}
