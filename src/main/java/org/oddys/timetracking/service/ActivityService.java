package org.oddys.timetracking.service;

import org.oddys.timetracking.dto.ActivityDto;

import java.util.List;

public interface ActivityService {
    long getNumberOfPages(long rowsPerPage) throws ServiceException;

    List<ActivityDto> findActivities(long currentPage, int rowsPerPage) throws ServiceException;

    boolean addActivity(String name) throws ServiceException;
}
