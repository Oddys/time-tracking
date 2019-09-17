package org.oddys.timetracking.dao;

import org.oddys.timetracking.dao.mysql.DaoException;
import org.oddys.timetracking.entity.UserActivity;

import java.util.List;

public interface UserActivityDao extends Dao<Long, UserActivity> {
    List<UserActivity> findAllByUserId(Long userId) throws DaoException;

    int add(Long userId, Long activityId, Boolean assigned, Boolean statusChangeRequested) throws DaoException;

    boolean exists(Long userId, Long activityId) throws DaoException;

    int requestStatusChange(Long userActivityId) throws DaoException;

    List<UserActivity> findAllStatusChangeRequested(long currentPage, int rowsPerPage) throws DaoException;

    long getNumberOfStatusChangeRequested() throws DaoException;
}
