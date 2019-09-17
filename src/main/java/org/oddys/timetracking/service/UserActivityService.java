package org.oddys.timetracking.service;

public interface UserActivityService {
    boolean addUserActivity(Long userId, Long activityId) throws ServiceException;

    boolean requestStatusChange(Long userActivityId) throws ServiceException;
}
