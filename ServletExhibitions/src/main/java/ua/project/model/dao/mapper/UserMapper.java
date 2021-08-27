package ua.project.model.dao.mapper;

import ua.project.model.entity.Role;
import ua.project.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
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

    @Override
    public User makeUnique(Map<Integer, User> cache, User user) {
        cache.putIfAbsent(user.getId(), user);
        return cache.get(user.getId());
    }
}
