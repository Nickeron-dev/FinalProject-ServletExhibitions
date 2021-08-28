package ua.project.model.dao;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface GenericDao<T> extends AutoCloseable {
    void create(T entity) throws SQLIntegrityConstraintViolationException;
    T findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(int id);
    void close();
}
