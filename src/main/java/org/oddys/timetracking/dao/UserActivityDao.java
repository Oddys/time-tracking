package org.oddys.timetracking.dao;

import org.oddys.timetracking.entity.UserActivity;

import java.util.List;

public interface UserActivityDao {
    List<UserActivity> findAllByUserId(Long userId);

    int add(Long userId, Long activityId, Boolean assigned, Boolean statusChangeRequested);

    boolean exists(Long userId, Long activityId);

    int requestStatusChange(Long userActivityId);

    List<UserActivity> findAllStatusChangeRequested(long currentPage, int rowsPerPage);

    long getNumberOfStatusChangeRequested();

    int updateAssignedAndStatusChangeRequested(Long userActivityId, boolean currentAssigned);

    UserActivity findByUserIdAndActivityId(Long userId, Long activityId);

    int update(UserActivity userActivity);

    UserActivity findById(Long id);
}
