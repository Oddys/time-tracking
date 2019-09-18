package org.oddys.timetracking.service;

import org.oddys.timetracking.dto.UserActivityDto;

import java.util.List;

public interface UserActivityService {
//    boolean addUserActivity(Long userId, Long activityId) throws ServiceException;

    boolean requestStatusChange(Long userActivityId) throws ServiceException;

    long getNumberOfPagesStatusChangeRequested(int rowsPerPage) throws ServiceException;

    List<UserActivityDto> findAllStatusChangeRequested(long currentPage, int rowsPerPage) throws ServiceException;

//    boolean processActivityRequest(Long userActivityId, boolean currentValue) throws ServiceException;

    boolean assignActivity(Long userId, Long activityId) throws ServiceException;

    boolean changeUserActivityStatus(Long userActivityId, boolean currentValue) throws ServiceException;
}
