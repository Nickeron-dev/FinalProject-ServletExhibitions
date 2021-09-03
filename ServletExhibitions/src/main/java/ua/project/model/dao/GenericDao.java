package ua.project.model.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

/**
 * @author Illia Koshkin
 */
public interface GenericDao<T> extends AutoCloseable {
    void create(T entity) throws SQLIntegrityConstraintViolationException;
    Optional<T> findById(int id);
    List<T> findAll();
    void close();
}
