package ua.project.model.services;

import ua.project.model.dao.DaoFactory;
import ua.project.model.dao.TicketDao;
import ua.project.model.entity.Ticket;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author Illia Koshkin
 */
public class TicketService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    /**
     * This method creates a new Ticket record in database
     * @param ticket Ticket object
     * @throws SQLIntegrityConstraintViolationException in case of invalid values given to SQL
     */
    public void saveNewTicket(Ticket ticket) throws SQLIntegrityConstraintViolationException {
        try(TicketDao ticketDao = daoFactory.createTicketDao()) {
            ticketDao.create(ticket);
        }
    }

    /**
     * Counts all tickets to an Exhibition by id
     * @param exhibitionId Id of an exhibition
     * @return amount of tickets in type long
     */
    public long countByExhibitionId(Integer exhibitionId) {
        try(TicketDao ticketDao = daoFactory.createTicketDao()) {
            return ticketDao.countByExhibitionId(exhibitionId);
        }
    }
}
