package ua.project.model.dao;

import ua.project.model.entity.Exhibition;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Illia Koshkin
 */
public interface ExhibitionDao extends GenericDao<Exhibition> {
    void cancelById(Integer id);
    void planById(Integer id);
    List<Exhibition> allByPage(Integer page);
    Integer pagesAvailable();
    List<Exhibition> filterByDate(LocalDate date);
}
