package org.oddys.timetracking.dao;

import org.oddys.timetracking.entity.Entity;

import java.util.List;
import java.util.Optional;

public interface Dao<K, T extends Entity> {
    K create(T entity);
    Optional<T> findById(K id);
    List<T> findAll();
    boolean update(T entity);
    boolean delete(K id);
}
