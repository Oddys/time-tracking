package org.oddys.timetracking.service;

import org.oddys.timetracking.dao.ActivityRecordDao;
import org.oddys.timetracking.dao.DaoFactoryProvider;
import org.oddys.timetracking.dao.UserActivityDao;
import org.oddys.timetracking.dto.ActivityRecordDto;
import org.oddys.timetracking.dto.ActivityRecordsPage;
import org.oddys.timetracking.entity.UserActivity;
import org.oddys.timetracking.util.ConfigManager;
import org.oddys.timetracking.util.ModelMapperWrapper;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActivityRecordServiceImpl implements ActivityRecordService {
    private static final ActivityRecordService INSTANCE = new ActivityRecordServiceImpl();
    private UserActivityDao userActivityDao;
    private ActivityRecordDao activityRecordDao;

    private ActivityRecordServiceImpl() {
        String dbmsName = ConfigManager.getInstance().getProperty(ConfigManager.DBMS);
        userActivityDao = DaoFactoryProvider.getInstance().getFactory(dbmsName).getUserActivityDao();
        activityRecordDao = DaoFactoryProvider.getInstance().getFactory(dbmsName).getActivityRecordDao();
    }

    public static ActivityRecordService getInstance() {
        return INSTANCE;
    }

    @Override
    public long getNumberOfPages(Long userActivityId, int rowsPerPage) {
        long numRows = activityRecordDao.getNumberOfRows(userActivityId);
        long numPages = numRows / rowsPerPage;
        return numRows % rowsPerPage == 0 ? numPages : ++numPages;
    }

    @Override
    public ActivityRecordsPage findActivityRecords(long userActivityId, long currentPage, int rowsPerPage) {
        ActivityRecordsPage page = new ActivityRecordsPage();
        UserActivity userActivity = userActivityDao.findById(userActivityId);
        page.setUserActivityData(userActivity);
        List<ActivityRecordDto> records = activityRecordDao.findAllByUserActivityId(userActivityId, currentPage, rowsPerPage)
                .stream()
                .map(ar -> ModelMapperWrapper.getMapper().map(ar, ActivityRecordDto.class))
                .collect(Collectors.toCollection(ArrayList::new));
        page.setElements(records);
        page.setNumPages(getNumberOfPages(userActivityId, rowsPerPage));
        page.setCurrentPage(currentPage);
        page.setRowsPerPage(rowsPerPage);
        return page;
    }

    @Override
    public int addActivityRecord(String dateString, String durationString,
            Long userActivityId) {
        try {
            LocalDate date = LocalDate.parse(dateString);
            Long duration = Long.valueOf(durationString);
            if (activityRecordDao.exists(date, userActivityId)) {
                return 0;
            }
            return activityRecordDao.add(date, duration, userActivityId);
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new ServiceException("ActivityRecordService failed to parse parameters", e); // FIXME
        }
    }
}
