package org.oddys.timetracking.dao;

import org.oddys.timetracking.dao.mysql.DaoException;
import org.oddys.timetracking.entity.Activity;

import java.util.List;

public interface ActivityDao extends Dao<Long, Activity> {
    long getNumberOfRows() throws DaoException;

    List<Activity> findAll(long currentPage, int rowsPerPage) throws DaoException;
}
