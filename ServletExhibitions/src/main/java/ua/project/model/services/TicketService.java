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

    public void saveNewTicket(Ticket ticket) throws SQLIntegrityConstraintViolationException {
        try(TicketDao ticketDao = daoFactory.createTicketDao()) {
            ticketDao.create(ticket);
        }
    }

    public long countByExhibitionId(Integer exhibitionId) {
        try(TicketDao ticketDao = daoFactory.createTicketDao()) {
            return ticketDao.countByExhibitionId(exhibitionId);
        }
    }
}
