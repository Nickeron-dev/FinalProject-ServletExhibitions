package ua.project.model.dao;

import ua.project.model.entity.User;

import java.util.Optional;

/**
 * @author Illia Koshkin
 */
public interface UserDao extends GenericDao<User>{
    Optional<User> findByName(String username);
}
