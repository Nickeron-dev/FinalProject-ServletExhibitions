package ua.project.model.services;

import ua.project.model.dao.DaoFactory;
import ua.project.model.dao.ExhibitionDao;
import ua.project.model.dao.UserDao;
import ua.project.model.entity.Exhibition;

import java.util.List;

public class ExhibitionService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Exhibition> findAll() {
        try(ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            return exhibitionDao.findAll();
        }
    }
}
