package org.oddys.timetracking.service;

public interface UserActivityService {
    boolean addUserActivity(Long userId, Long activityId);
}
