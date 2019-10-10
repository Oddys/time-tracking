package org.oddys.timetracking.service;

import org.oddys.timetracking.dto.PageDto;
import org.oddys.timetracking.dto.UserActivitiesDto;
import org.oddys.timetracking.dto.UserActivityDto;

public interface UserActivityService {
    boolean requestUserActivityStop(Long userActivityId);

    long getNumberOfPagesStatusChangeRequested(int rowsPerPage);

    PageDto<UserActivityDto> findAllStatusChangeRequested(long currentPage, int rowsPerPage);

    boolean requestActivityAssigned(Long userId, Long activityId);

    boolean changeStatus(Long userActivityId, boolean currentValue);

    UserActivitiesDto searchUserActivitiesByUserId(Long userId);
}
