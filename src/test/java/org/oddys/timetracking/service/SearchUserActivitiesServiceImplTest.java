package org.oddys.timetracking.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.oddys.timetracking.dao.UserActivityDao;
import org.oddys.timetracking.dao.UserDao;
import org.oddys.timetracking.dto.UserActivitiesDto;
import org.oddys.timetracking.dto.UserActivityDto;
import org.oddys.timetracking.entity.Activity;
import org.oddys.timetracking.entity.Role;
import org.oddys.timetracking.entity.User;
import org.oddys.timetracking.entity.UserActivity;
import org.oddys.timetracking.util.ModelMapperWrapper;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchUserActivitiesServiceImplTest {
    @Mock
    private UserActivityDao userActivityDao;

    @Mock
    private UserDao userDao;

    private final ModelMapper mapper = ModelMapperWrapper.getMapper();

    @InjectMocks
    private final SearchUserActivitiesService service = SearchUserActivitiesServiceImpl.getInstance();

    @Test
    public void returnEmptyDtoIfUserDoesNotExist() {
        long userId = 1L;
        when(userDao.findById(userId)).thenReturn(null);
        assertEquals(new UserActivitiesDto(), service.searchUserActivitiesByUserId(userId));
    }

    @Test
    public void returnDtoWithDataIfUserExists() {
        long userId = 1L;
        Role role = new Role(2L, "USER");
        User user = new User(1L, "login", "password", "John", "Doe", role);
        when(userDao.findById(userId)).thenReturn(user);
        Activity activity1 = new Activity(1L, "Doing Stuff");
        Activity activity2 = new Activity(1L, "Doing Another Stuff");
        List<UserActivity> activities = List.of(
             new UserActivity(1L, true, false, user, activity1),
             new UserActivity(2L, true, false, user, activity2)
        );
        when(userActivityDao.findAllByUserId(userId)).thenReturn(activities);
        List<UserActivityDto> dtos = activities.stream()
                .map(ua -> mapper.map(ua, UserActivityDto.class))
                .collect(Collectors.toList());
        UserActivitiesDto expected = new UserActivitiesDto(userId, user.getFirstName(),
                user.getLastName(), dtos);
        assertEquals(expected, service.searchUserActivitiesByUserId(userId));
    }
}
