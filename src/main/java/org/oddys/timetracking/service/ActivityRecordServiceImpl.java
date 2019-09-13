package org.oddys.timetracking.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.dao.ActivityRecordDao;
import org.oddys.timetracking.dao.DaoFactoryProvider;
import org.oddys.timetracking.dao.mysql.DaoException;
import org.oddys.timetracking.dto.ActivityRecordDto;
import org.oddys.timetracking.entity.ActivityRecord;
import org.oddys.timetracking.util.ConfigManager;
import org.oddys.timetracking.util.ModelMapperWrapper;

import java.sql.Date;
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
    public long getNumberOfPages(Long userActivityId, int rowsPerPage) throws ServiceException {
        try {
            long numRows = dao.getNumberOfRows(userActivityId);
            long numPages = numRows / rowsPerPage;
            return numRows % rowsPerPage == 0 ? numPages : ++numPages;
        } catch (DaoException e) {
            throw new ServiceException("Failed to obtain number of rows", e);
        }
    }

    @Override
    public List<ActivityRecordDto> findActivityRecords(long userActivityId, long currentPage, int recordsPerPage) throws ServiceException {
        try {
            return dao.findAllByUserActivityId(userActivityId, currentPage, recordsPerPage)
                    .stream()
                    .map(ar -> ModelMapperWrapper.getMapper().map(ar, ActivityRecordDto.class))
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (DaoException e) {
            throw new ServiceException("Failed to find ActivityRecords", e);
        }
    }

    @Override
    public int addActivityRecord(String dateString, String durationString,
            Long userActivityId) throws ServiceException {
        try {
            Date date = Date.valueOf(dateString);
            Long duration = Long.valueOf(durationString);
            if (dao.exists(date, userActivityId)) {
                return 0;
            }
            return dao.add(date, duration, userActivityId);
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new ServiceException("ActivityRecordService failed to parse parameters", e);
        } catch (DaoException e) {
            throw new ServiceException("ActivityRecordService failed to add ActivityRecord", e);
        }
    }
}
