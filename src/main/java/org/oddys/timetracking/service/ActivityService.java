package org.oddys.timetracking.service;

import org.oddys.timetracking.dto.ActivityDto;

import java.util.List;

public interface ActivityService {
    long getNumberOfPages(long rowsPerPage);

    List<ActivityDto> findActivities(long currentPage, int rowsPerPage);

    boolean addActivity(String name);
}
