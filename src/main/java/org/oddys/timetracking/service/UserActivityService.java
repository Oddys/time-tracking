package org.oddys.timetracking.service;

import org.oddys.timetracking.dto.PageDto;
import org.oddys.timetracking.dto.UserActivitiesDto;
import org.oddys.timetracking.dto.UserActivityDto;

/**
 * This interface provides functionality related to UserActivities.
 */
public interface UserActivityService {
    /**
     * Sends a request for stopping this activity.
     *
     * @param userActivityId the id of a UserActivity to be stopped
     * @return true if request was sent successfully, false otherwise
     */
    boolean requestUserActivityStop(Long userActivityId);

    /**
     * Computes the number of pages in which available user activities
     * that wait for the status change can be displayed.
     *
     * @param rowsPerPage a maximum number of rows that can be displayed on a page
     * @return a number of pages
     */
    long getNumberOfPagesStatusChangeRequested(int rowsPerPage);

    /**
     * Retrieves a specified page of user activities that wait for the status change.
     *
     * @param currentPage a current page number
     * @param rowsPerPage a maximum number of rows that can be displayed on a page
     * @return a PageDto object containing the information about this page and
     * the retrieved UserActivities
     */
    PageDto<UserActivityDto> findAllStatusChangeRequested(long currentPage, int rowsPerPage);

    /**
     * Sends a request for assigning the activity to this user.
     *
     * @param userId the id of the user requesting for the activity
     * @param activityId the id of the requested activity
     * @return true if the request was sent successfully, false otherwise
     */
    boolean requestActivityAssigned(Long userId, Long activityId);

    /**
     * Reverts the status of this activity.
     *
     * @param userActivityId the if of the activity whose status should be changed
     * @param currentValue the current status
     * @return true if the status was changed successfully, false otherwise
     */
    boolean changeStatus(Long userActivityId, boolean currentValue);

    /**
     * Retrieve all activities associated with a given user.
     *
     * @param userId the id of the user whose activities should be retrieved
     * @return a UserActivitiesDto containing the retrieved UserActivities and
     * the metadata of the request
     */
    UserActivitiesDto searchUserActivitiesByUserId(Long userId);
}
