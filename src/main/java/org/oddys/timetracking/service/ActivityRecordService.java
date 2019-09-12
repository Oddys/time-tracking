package org.oddys.timetracking.service;

import org.oddys.timetracking.dto.ActivityRecordDto;

import java.util.List;

public interface ActivityRecordService {
    long getNumberOfRows() throws ServiceException;

    List<ActivityRecordDto> findActivityRecords(long currentPage, int recordPerPage) throws ServiceException;
}
