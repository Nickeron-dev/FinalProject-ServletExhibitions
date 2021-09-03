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
     * @throws SQLIntegrityConstraintViolationException in case of invalid input
     */
    public void saveNewExhibition(Exhibition exhibition) throws SQLIntegrityConstraintViolationException {
        try(ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            exhibitionDao.create(exhibition);
        }
    }

    /**
     * This method gets Exhibition by id
     * @param id Id of an exhibition
     * @return Optional of Exhibition
     */
    public Optional<Exhibition> findById(int id) {
        try(ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            return exhibitionDao.findById(id);
        }
    }

    /**
     * Cancels Exhibition by id
     * @param id Id of an Exhibition
     */
    public void cancelById(Integer id) {
        try(ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            exhibitionDao.cancelById(id);
        }
    }

    /**
     * Plans Exhibition by id
     * @param id Id of an Exhibition
     */
    public void planById(Integer id) {
        try(ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            exhibitionDao.planById(id);
        }
    }

    /**
     * Divides all exhibitions by pages and gives certain page
     * @param page Number of a required page
     * @return util.List with all exhibitions from required page
     */
    public List<Exhibition> allByPage(Integer page) {
        try(ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            return exhibitionDao.allByPage(page);
        }
    }

    /**
     * Integer amount of pages
     * @return Integer value with amount of pages
     */
    public Integer pagesAvailable() {
        try(ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            return exhibitionDao.pagesAvailable();
        }
    }

    /**
     * Filters all exhibitions by date
     * @param date LocalDate object that will be used to filter
     * @return util.List of exhibitions
     */
    public List<Exhibition> filterByDate(LocalDate date) {
        try(ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            return exhibitionDao.filterByDate(date);
        }
    }
}
