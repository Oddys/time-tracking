package org.oddys.timetracking.service;

import org.oddys.timetracking.dao.ActivityDao;
import org.oddys.timetracking.dao.DaoFactoryProvider;
import org.oddys.timetracking.dao.mysql.DaoException;
import org.oddys.timetracking.dto.ActivityDto;
import org.oddys.timetracking.util.ConfigManager;
import org.oddys.timetracking.util.ModelMapperWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActivityServiceImpl implements ActivityService {
    private static final ActivityService INSTANCE = new ActivityServiceImpl();
    private ActivityDao dao;

    private ActivityServiceImpl() {
        String dbmsName = ConfigManager.getInstance().getProperty(ConfigManager.DBMS);
        dao = DaoFactoryProvider.getInstance().getFactory(dbmsName).getActivityDao();
    }

    public static ActivityService getInstance() {
        return INSTANCE;
    }

    @Override
    public long getNumberOfPages(long rowsPerPage) throws ServiceException {
        try {
            long numRows = dao.getNumberOfRows();
            long numPages = numRows / rowsPerPage;
            return numRows % rowsPerPage == 0 ? numPages : ++numPages;
        } catch (DaoException e) {
            throw new ServiceException("Failed to get the number of pages", e);
        }
    }

    @Override
    public List<ActivityDto> findActivities(long currentPage, int rowsPerPage) throws ServiceException {
        try {
            return dao.findAll(currentPage, rowsPerPage).stream()
                    .map(a -> ModelMapperWrapper.getMapper().map(a, ActivityDto.class))
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (DaoException e) {
            throw new ServiceException("Failed to find Activities");
        }
    }
}
