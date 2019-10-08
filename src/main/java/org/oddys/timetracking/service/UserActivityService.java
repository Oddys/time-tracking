package org.oddys.timetracking.service;

import org.oddys.timetracking.dto.PageDto;
import org.oddys.timetracking.dto.UserActivityDto;

public interface UserActivityService {
    boolean requestStatusChange(Long userActivityId);

    long getNumberOfPagesStatusChangeRequested(int rowsPerPage);

    PageDto<UserActivityDto> findAllStatusChangeRequested(long currentPage, int rowsPerPage);

    boolean assignActivity(Long userId, Long activityId);

    boolean changeUserActivityStatus(Long userActivityId, boolean currentValue);
}
