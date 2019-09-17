package org.oddys.timetracking.dao;

import org.oddys.timetracking.dao.mysql.DaoException;
import org.oddys.timetracking.entity.UserActivity;

import java.util.List;

public interface UserActivityDao extends Dao<Long, UserActivity> {
    List<UserActivity> findAllByUserId(Long userId) throws DaoException;

    boolean addUserActivity(UserActivity activity);
}
