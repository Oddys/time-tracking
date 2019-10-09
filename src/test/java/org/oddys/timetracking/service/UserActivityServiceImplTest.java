package org.oddys.timetracking.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.oddys.timetracking.dao.UserActivityDao;
import org.oddys.timetracking.entity.UserActivity;
import org.oddys.timetracking.testdata.EntityStubProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserActivityServiceImplTest {
    @Mock
    private UserActivityDao userActivityDao;

    @InjectMocks
    private final UserActivityService SERVICE = UserActivityServiceImpl.getInstance();

    private final EntityStubProvider ENTITY_PROVIDER = EntityStubProvider.getInstance();

    private final long USER_ID = 1L;
    private final long USER_ACTIVITY_ID = 1L;

    @Test
    public void addUserActivityIfUserActivityDoesNotExist() {
        when(userActivityDao.add(any(long.class), eq(USER_ACTIVITY_ID), eq(false), eq(true))).thenReturn(1);
        assertTrue(SERVICE.requestActivityAssigned(USER_ID, USER_ACTIVITY_ID));
    }

    @Test
    public void doNotChangeStatusIfStatusChangeIsNotRequested() {
        UserActivity userActivity = ENTITY_PROVIDER.getUserActivityAssignedAndStatusChangeNotRequestedStub();
        when(userActivityDao.findByUserIdAndActivityId(USER_ID,
                USER_ACTIVITY_ID)).thenReturn(userActivity);
        assertFalse(SERVICE.requestActivityAssigned(USER_ID, USER_ACTIVITY_ID));
    }

    @Test
    public void requestUserActivityIfNotAssignedAndStatusChangeIsNotRequested() {
        UserActivity userActivity = ENTITY_PROVIDER.getUserActivityNotAssignedAndStatusChangeNotRequestedStub();
        when(userActivityDao.findByUserIdAndActivityId(USER_ID, USER_ACTIVITY_ID))
                .thenReturn(userActivity);
        when(userActivityDao.update(any(UserActivity.class))).thenReturn(1);
        assertTrue(SERVICE.requestActivityAssigned(USER_ID, USER_ACTIVITY_ID));
        assertTrue(userActivity.getStatusChangeRequested());
        verify(userActivityDao).update(any(UserActivity.class));
    }

    @Test
    public void returnTwoIfNumRowsMoreThanRowsPerPage() {
        int rowsPerPage = 5;
        when(userActivityDao.getNumberOfStatusChangeRequested()).thenReturn(
                rowsPerPage + 1L);
        long expectedPageCount = 2;
        assertEquals(expectedPageCount, SERVICE.getNumberOfPagesStatusChangeRequested(rowsPerPage));
    }

    @Test
    public void returnOneIfNumRowsEqualsRowsPerPage() {
        int rowsPerPage = 5;
        when(userActivityDao.getNumberOfStatusChangeRequested()).thenReturn((long) rowsPerPage);
        assertEquals(1, SERVICE.getNumberOfPagesStatusChangeRequested(rowsPerPage));
    }

    @Test
    public void changeStatusIfRequested() {
        UserActivity userActivity = ENTITY_PROVIDER.getUserActivityAssignedAndStatusChangeRequestedStub();
        when(userActivityDao.findById(USER_ACTIVITY_ID)).thenReturn(userActivity);
        SERVICE.changeStatus(USER_ACTIVITY_ID, true);
        verify(userActivityDao).updateAssignedAndStatusChangeRequested(
                USER_ACTIVITY_ID, false);
    }

    @Test
    public void doNotChangeStatusIfNotRequested() {
        UserActivity userActivity = ENTITY_PROVIDER.getUserActivityAssignedAndStatusChangeNotRequestedStub();
        when(userActivityDao.findById(USER_ACTIVITY_ID)).thenReturn(userActivity);
        assertFalse(SERVICE.changeStatus(USER_ACTIVITY_ID, true));
    }
}
