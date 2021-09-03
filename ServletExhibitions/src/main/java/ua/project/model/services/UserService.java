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
    public Optional<User> login(String name){
        Optional<User> result;
        try(UserDao userDao = daoFactory.createUserDao()){
            result = userDao.findByName(name);
        }
        return result;
    }

    public void saveNewUser(User user) throws SQLIntegrityConstraintViolationException {
        try(UserDao userDao = daoFactory.createUserDao()) {
            userDao.create(user);
        }
    }
}
