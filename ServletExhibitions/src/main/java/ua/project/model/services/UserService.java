package ua.project.model.services;

import ua.project.model.dao.DaoFactory;
import ua.project.model.dao.UserDao;
import ua.project.model.entity.User;

import java.util.Optional;

public class UserService {

    DaoFactory daoFactory = DaoFactory.getInstance();
    public Optional<User> login(String name){
        Optional<User> result;
        try(UserDao userDao = daoFactory.createUserDao()){
            result = userDao.findByName(name);
        }
        return result;
    }
}