package org.oddys.timetracking.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.dao.ActivityRecordDao;
import org.oddys.timetracking.dao.DaoFactoryProvider;
import org.oddys.timetracking.dao.mysql.DaoException;
import org.oddys.timetracking.dto.ActivityRecordDto;
import org.oddys.timetracking.util.ConfigManager;
import org.oddys.timetracking.util.ModelMapperWrapper;

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
    public long getNumberOfPages(int rowsPerPage) throws ServiceException {
        try {
            long numRows = dao.getNumberOfRows();
            long numPages = numRows / rowsPerPage;
            return numRows % rowsPerPage == 0 ? numPages : ++numPages;
        } catch (DaoException e) {
            throw new ServiceException("Failed to obtain number of rows", e);
        }
    }

    @Override
    public List<ActivityRecordDto> findActivityRecords(long currentPage, int recordsPerPage) throws ServiceException {
        try {
            return dao.findActivityRecords(currentPage, recordsPerPage).stream()
                    .map(ar -> ModelMapperWrapper.getMapper().map(ar, ActivityRecordDto.class))
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (DaoException e) {
            throw new ServiceException("Failed to find ActivityRecords", e);
        }
    }
}
