package org.oddys.timetracking.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, T> {
    K create(T entity);
    Optional<T> findById(K id);
    List<T> findAll();
    boolean update(T entity);
    boolean delete(K id);
}
