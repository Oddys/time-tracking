package org.oddys.timetracking.service;

import org.oddys.timetracking.dto.UserActivitiesDto;

public interface SearchUserActivitiesService {
    UserActivitiesDto searchUserActivitiesByUserId(Long userId);
}
