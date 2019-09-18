package org.oddys.timetracking.dao;

import org.oddys.timetracking.dao.mysql.DaoException;
import org.oddys.timetracking.entity.Entity;

import java.util.List;

public interface Dao<K, T extends Entity> {
    K create(T entity) throws DaoException;
    T findById(K id) throws DaoException;
    List<T> findAll() throws DaoException;
    int update(T entity) throws DaoException;
    boolean delete(K id) throws DaoException;
}
