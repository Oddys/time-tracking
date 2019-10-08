package org.oddys.timetracking.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.dao.ActivityRecordDao;
import org.oddys.timetracking.dao.DaoFactoryProvider;
import org.oddys.timetracking.dto.ActivityRecordDto;
import org.oddys.timetracking.dto.ActivityRecordsPage;
import org.oddys.timetracking.util.ConfigManager;
import org.oddys.timetracking.util.ModelMapperWrapper;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActivityRecordServiceImpl implements ActivityRecordService {
    private static final Logger log = LogManager.getLogger();
    private static final ActivityRecordService INSTANCE = new ActivityRecordServiceImpl();
    private ActivityRecordDao dao;

    private ActivityRecordServiceImpl() {
        String dbmsName = ConfigManager.getInstance().getProperty(ConfigManager.DBMS);
        dao = DaoFactoryProvider.getInstance().getFactory(dbmsName).getActivityRecordDao();
    }

    public static ActivityRecordService getInstance() {
        return INSTANCE;
    }

    @Override
    public long getNumberOfPages(Long userActivityId, int rowsPerPage) {
        long numRows = dao.getNumberOfRows(userActivityId);
        long numPages = numRows / rowsPerPage;
        return numRows % rowsPerPage == 0 ? numPages : ++numPages;
    }

//    @Override
//    public List<ActivityRecordDto> findActivityRecords(long userActivityId, long currentPage, int recordsPerPage) {
//        return dao.findAllByUserActivityId(userActivityId, currentPage, recordsPerPage)
//                .stream()
//                .map(ar -> ModelMapperWrapper.getMapper().map(ar, ActivityRecordDto.class))
//                .collect(Collectors.toCollection(ArrayList::new));
//    }

    @Override
    public ActivityRecordsPage findActivityRecords(long userActivityId, long currentPage, int rowsPerPage) {
        ActivityRecordsPage page = new ActivityRecordsPage();
        List<ActivityRecordDto> records = dao.findAllByUserActivityId(userActivityId, currentPage, rowsPerPage)
                .stream()
                .map(ar -> ModelMapperWrapper.getMapper().map(ar, ActivityRecordDto.class))
                .collect(Collectors.toCollection(ArrayList::new));
        page.setNumPages(getNumberOfPages(userActivityId, rowsPerPage));
        page.setElements(records);
        page.setUserActivityId(userActivityId);
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
            if (dao.exists(date, userActivityId)) {
                return 0;
            }
            return dao.add(date, duration, userActivityId);
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new ServiceException("ActivityRecordService failed to parse parameters", e); // FIXME
        }
    }
}
