package ua.project.model.dao;

import ua.project.model.entity.Ticket;

/**
 * @author Illia Koshkin
 */
public interface TicketDao extends GenericDao<Ticket> {
    long countByExhibitionId(Integer exhibitionId);
}
