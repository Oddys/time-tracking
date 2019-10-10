package org.oddys.timetracking.service;

import org.oddys.timetracking.dto.ActivityRecordsPage;

import java.time.LocalDate;

/**
 * This interface provides functionality related to ActivityRecords.
 */
public interface ActivityRecordService {
    /**
     * Computes the number of pages in which available records
     * of this user activity can be displayed.
     *
     * @param userActivityId a user activity id
     * @param rowsPerPage a maximum number of rows that can be displayed on a page
     * @return a number of pages
     */
    long getNumberOfPages(Long userActivityId, int rowsPerPage);

    /**
     * Retrieves a specified page of records for this user activity.
     *
     * @param userActivityId a user activity id
     * @param currentPage a current page number
     * @param rowsPerPage a maximum number of rows that can be displayed on a page
     * @return
     */
    ActivityRecordsPage findActivityRecords(long userActivityId, long currentPage,
            int rowsPerPage);

    /**
     * Attempts to store a new user activity record.
     *
     * @param date a date for which a new record should be created
     * @param duration a time spent on performing the activity
     * @param userActivityId a user activity id
     *
     * @return true if the new record was stored successfully, false otherwise
     */
    boolean addActivityRecord(LocalDate date, long duration, long userActivityId);
}
