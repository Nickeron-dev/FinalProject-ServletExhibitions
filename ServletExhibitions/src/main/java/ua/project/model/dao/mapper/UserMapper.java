package ua.project.model.dao.mapper;

import ua.project.model.entity.Role;
import ua.project.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author Illia Koshkin
 */
public class UserMapper implements ObjectMapper<User> {
    /**
     * This method takes User from a ResultSet
     * @param resultSet ResultSet of an SQL statement
     * @return User object
     * @throws SQLException if nothing was found
     */
    @Override
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        if (resultSet.getString("role").equals("ADMIN")) {
            user.setRole(Role.ADMIN);
        }
        if (resultSet.getString("role").equals("USER")) {
            user.setRole(Role.USER);
        }
        if (resultSet.getString("role").equals("GUEST")) {
            user.setRole(Role.GUEST);
        }
        return user;
    }

    /**
     * Removes all repeated values
     * @param cache Map with users
     * @param user Object of a user that will be put if absent
     * @return User from Map with the same id
     */
    @Override
    public User makeUnique(Map<Integer, User> cache, User user) {
        cache.putIfAbsent(user.getId(), user);
        return cache.get(user.getId());
    }
}
