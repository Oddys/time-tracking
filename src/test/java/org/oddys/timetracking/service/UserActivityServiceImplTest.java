package org.oddys.timetracking.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.oddys.timetracking.dao.UserActivityDao;
import org.oddys.timetracking.entity.UserActivity;
import org.oddys.timetracking.testdata.EntityStubProvider;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserActivityServiceImplTest {
    @Mock
    private UserActivityDao userActivityDao;

    @InjectMocks
    private final UserActivityService SERVICE = UserActivityServiceImpl.getInstance();

    private final EntityStubProvider ENTITY_PROVIDER = EntityStubProvider.getInstance();

    @Test
    public void addUserActivityIfUserActivityDoesNotExist() {
        long userActivityId = 1L;
        when(userActivityDao.add(any(long.class), eq(userActivityId), eq(false), eq(true))).thenReturn(1);
        long userId = 1L;
        assertTrue(SERVICE.assignActivity(userId, userActivityId));
    }

    @Test
    public void returnFalseIfStatusChangeIsNotRequested() {
        long userId = 1L;
        long userActivityId = 1L;
        UserActivity userActivity = ENTITY_PROVIDER.getUserActivityStatusChangeNotRequestedStub();
        when(userActivityDao.findByUserIdAndActivityId(userId, userActivityId)).thenReturn(userActivity);
        assertFalse(SERVICE.assignActivity(userId, userActivityId));
    }
}
