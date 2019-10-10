package org.oddys.timetracking.service;

import org.oddys.timetracking.dto.ActivityDto;

import java.util.List;

/**
 * This interface provides functionality related to Activities.
 */
public interface ActivityService {
    /**
     * Computes the number of pages in which available activities
     * can be displayed.
     *
     * @param rowsPerPage a maximum number of rows that can be displayed on a page
     * @return a number of pages
     */
    long getNumberOfPages(long rowsPerPage);

    /**
     * Retrieves a specified page of activities.
     *
     * @param currentPage a current page number
     * @param rowsPerPage a maximum number of rows that can be displayed on a page
     * @return
     */
    List<ActivityDto> findActivities(long currentPage, int rowsPerPage);

    /**
     * Attempts to store a new activity.
     *
     * @param name a name of the new activity
     *
     * @return true if the new activity was stored successfully, false otherwise
     */
    boolean addActivity(String name);
}
