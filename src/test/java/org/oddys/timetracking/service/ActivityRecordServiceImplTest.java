package org.oddys.timetracking.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.oddys.timetracking.dao.ActivityRecordDao;
import org.oddys.timetracking.dao.UserActivityDao;
import org.oddys.timetracking.dto.ActivityRecordDto;
import org.oddys.timetracking.dto.ActivityRecordsPage;
import org.oddys.timetracking.entity.Activity;
import org.oddys.timetracking.entity.ActivityRecord;
import org.oddys.timetracking.entity.Role;
import org.oddys.timetracking.entity.User;
import org.oddys.timetracking.entity.UserActivity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ActivityRecordServiceImplTest {
    @Mock
    private UserActivityDao userActivityDao;

    @Mock
    private ActivityRecordDao activityRecordDao;

    @InjectMocks
    private final ActivityRecordService service = ActivityRecordServiceImpl.getInstance();

    @Test
    public void returnTwoIfNumRowsMoreThanRowsPerPage() {
        int rowsPerPage = 5;
        when(activityRecordDao.getNumberOfRows(any(Long.class))).thenReturn(
                rowsPerPage + 1L);
        long expectedPageCount = 2;
        assertEquals(expectedPageCount, service.getNumberOfPages(1L, rowsPerPage));
    }

    @Test
    public void returnOneIfNumRowsEqualsRowsPerPage() {
        int rowsPerPage = 5;
        when(activityRecordDao.getNumberOfRows(any(Long.class))).thenReturn((long) rowsPerPage);
        assertEquals(1, service.getNumberOfPages(1L, rowsPerPage));
    }

    @Test
    public void returnActivityRecordsPageIfUserActivityExists() {
        Role role = new Role(2L, "USER");
        User user = new User(1L, "login", "password", "John", "Doe", role);
        Activity activity = new Activity(1L, "Doing stuff");
        UserActivity userActivity = new UserActivity(1L, true, false, user, activity);
        List<ActivityRecord> records = List.of(
                new ActivityRecord(1L, LocalDate.of(2019, 1, 1), 120L, userActivity),
                new ActivityRecord(2L, LocalDate.of(2019, 1, 2), 360L, userActivity)
        );
        when(userActivityDao.findById(1L)).thenReturn(userActivity);
        when(activityRecordDao.findAllByUserActivityId(any(long.class), any(long.class), any(int.class)))
                .thenReturn(records);
        when(activityRecordDao.getNumberOfRows(any(Long.class))).thenReturn(1L);
        ActivityRecordsPage expected = new ActivityRecordsPage();
        expected.setUserActivityData(userActivity);
        List<ActivityRecordDto> recordDtos = List.of(
                new ActivityRecordDto(1L, "Doing stuff", false, LocalDate.of(2019, 1, 1), 120L, 1L, "John", "Doe", 1L),
                new ActivityRecordDto(2L, "Doing stuff", false, LocalDate.of(2019, 1, 2), 360L, 1L, "John", "Doe", 1L)
        );
        expected.setElements(recordDtos);
        expected.setNumPages(1L);
        expected.setCurrentPage(1L);
        expected.setRowsPerPage(5);
        assertEquals(expected, service.findActivityRecords(1L, 1L, 5));
    }
}
