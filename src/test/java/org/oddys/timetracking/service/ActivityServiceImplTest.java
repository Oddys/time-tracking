package org.oddys.timetracking.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.oddys.timetracking.dao.ActivityDao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ActivityServiceImplTest {
    @Mock
    private ActivityDao activityDao;

    @InjectMocks
    private final ActivityService service = ActivityServiceImpl.getInstance();

    @Test
    public void returnTwoIfNumRowsMoreThanRowsPerPage() {
        int rowsPerPage = 5;
        when(activityDao.getNumberOfRows()).thenReturn(
                rowsPerPage + 1L);
        long expectedPageCount = 2;
        assertEquals(expectedPageCount, service.getNumberOfPages(rowsPerPage));
    }

    @Test
    public void returnOneIfNumRowsEqualsRowsPerPage() {
        int rowsPerPage = 5;
        when(activityDao.getNumberOfRows()).thenReturn((long) rowsPerPage);
        assertEquals(1, service.getNumberOfPages(rowsPerPage));
    }
}
