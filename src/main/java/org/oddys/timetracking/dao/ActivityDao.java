package org.oddys.timetracking.dao;

import org.oddys.timetracking.entity.Activity;

import java.util.List;

public interface ActivityDao {
    boolean add(String activityName);

    Activity findByName(String activityName);

    long getNumberOfRows();

    List<Activity> findAll(long currentPage, int rowsPerPage);
}
