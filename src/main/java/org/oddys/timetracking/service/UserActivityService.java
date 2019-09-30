package org.oddys.timetracking.service;

import org.oddys.timetracking.dto.UserActivityDto;

import java.util.List;

public interface UserActivityService {
    boolean requestStatusChange(Long userActivityId);

    long getNumberOfPagesStatusChangeRequested(int rowsPerPage);

    List<UserActivityDto> findAllStatusChangeRequested(long currentPage, int rowsPerPage);

    boolean assignActivity(Long userId, Long activityId);

    boolean changeUserActivityStatus(Long userActivityId, boolean currentValue);
}
