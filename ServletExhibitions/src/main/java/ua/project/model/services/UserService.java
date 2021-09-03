package ua.project.model.services;

import ua.project.model.dao.DaoFactory;
import ua.project.model.dao.UserDao;
import ua.project.model.entity.User;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

/**
 * @author Illia Koshkin
 */
public class UserService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    /**
     * Username of a User
     * @param name String object with username
     * @return Optional of User
     */
    public Optional<User> login(String name){
        Optional<User> result;
        try(UserDao userDao = daoFactory.createUserDao()){
            result = userDao.findByName(name);
        }
        return result;
    }

    /**
     * This method creates a new User record in database
     * @param user User object
     * @throws SQLIntegrityConstraintViolationException in case of invalid input
     */
    public void saveNewUser(User user) throws SQLIntegrityConstraintViolationException {
        try(UserDao userDao = daoFactory.createUserDao()) {
            userDao.create(user);
        }
    }
}
