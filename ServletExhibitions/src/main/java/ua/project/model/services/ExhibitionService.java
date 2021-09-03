package ua.project.model.services;

import ua.project.model.dao.DaoFactory;
import ua.project.model.dao.ExhibitionDao;
import ua.project.model.entity.Exhibition;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Illia Koshkin
 */
public class ExhibitionService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    /**
     * This method gets all Exhibitions and closes connection
     * @return util.List of all exhibitions
     */
    public List<Exhibition> findAll() {
        try(ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            return exhibitionDao.findAll();
        }
    }

    /**
     * This method creates new Exhibition Record
     * @param exhibition Exhibition object
     * @throws SQLIntegrityConstraintViolationException
     */
    public void saveNewExhibition(Exhibition exhibition) throws SQLIntegrityConstraintViolationException {
        try(ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            exhibitionDao.create(exhibition);
        }
    }

    public Optional<Exhibition> findById(int id) {
        try(ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            return exhibitionDao.findById(id);
        }
    }

    public void cancelById(Integer id) {
        try(ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            exhibitionDao.cancelById(id);
        }
    }

    public void planById(Integer id) {
        try(ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            exhibitionDao.planById(id);
        }
    }

    public List<Exhibition> allByPage(Integer page) {
        try(ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            return exhibitionDao.allByPage(page);
        }
    }

    public Integer pagesAvailable() {
        try(ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            return exhibitionDao.pagesAvailable();
        }
    }

    public List<Exhibition> filterByDate(LocalDate date) {
        try(ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            return exhibitionDao.filterByDate(date);
        }
    }
}
