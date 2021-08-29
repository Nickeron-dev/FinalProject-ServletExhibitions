package ua.project.model.dao;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {
    void create(T entity) throws SQLIntegrityConstraintViolationException;
    Optional<T> findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(int id);
    void close();
}
