package org.oddys.timetracking.dao;

import org.oddys.timetracking.dao.mysql.DaoException;
import org.oddys.timetracking.entity.Activity;

import java.util.List;

public interface ActivityDao {
    boolean create(String activityName) throws DaoException;

//    Activity findById(Long id);

    long getNumberOfRows() throws DaoException;

    List<Activity> findAll(long currentPage, int rowsPerPage) throws DaoException;

//    List<Activity> findAll() throws DaoException;

//    int update(Activity entity) throws DaoException;
}
