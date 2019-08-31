package org.oddys.timetracking.dao;

import java.util.List;

public interface Dao<K, T> {
    K create(T entity);
    T findById(K id);
    List<T> findAll();
    boolean update(T entity);
    boolean delete(K id);
}
