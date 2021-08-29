package ua.project.model.dao;

import ua.project.model.entity.Exhibition;

import java.util.List;

public interface ExhibitionDao extends GenericDao<Exhibition> {
    void cancelById(Integer id);
    void planById(Integer id);
    List<Exhibition> allByPage(Integer page);
    Integer pagesAvailable();
}
