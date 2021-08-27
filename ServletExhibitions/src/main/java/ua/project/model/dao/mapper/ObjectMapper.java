package ua.project.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface ObjectMapper<T> {

    T extractFromResultSet(ResultSet resultSet) throws SQLException;

    T makeUnique(Map<Integer, T> cache, T user);
}
