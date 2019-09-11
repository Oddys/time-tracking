package org.oddys.timetracking.service;

import org.oddys.timetracking.dto.UserActivityDto;

import java.util.List;

public interface SearchUserActivitiesService {
    List<UserActivityDto> searchUserActivitiesByUserId(Long userId) throws ServiceException;
}
