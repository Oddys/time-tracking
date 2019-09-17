package org.oddys.timetracking.service;

import org.oddys.timetracking.dto.UserActivityDto;

import java.util.List;

public interface UserActivityService {
    boolean addUserActivity(Long userId, Long activityId) throws ServiceException;

    boolean requestStatusChange(Long userActivityId) throws ServiceException;

    List<UserActivityDto> findAllStatusChangeRequested() throws ServiceException;
}
