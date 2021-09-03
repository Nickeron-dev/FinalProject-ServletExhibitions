package ua.project.model.dao.mapper;

import ua.project.model.entity.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author Illia Koshkin
 */
public class TicketMapper implements ObjectMapper<Ticket> {
    /**
     * This method takes Ticket from a ResultSet
     * @param resultSet ResultSet of an SQL statement
     * @return Ticket object
     * @throws SQLException if nothing was found
     */
    @Override
    public Ticket extractFromResultSet(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(resultSet.getInt("id"));
        ticket.setExhibitionTopic(resultSet.getString("exhibitionTopic"));
        ticket.setExhibitionId(Integer.parseInt(resultSet.getString("exhibitionId")));
        ticket.setUserEmail(resultSet.getString("userEmail"));
        ticket.setUserId(Integer.parseInt(resultSet.getString("userId")));
        return ticket;
    }

    /**
     * Removes all repeated values
     * @param cache Map with tickets
     * @param ticket Object of a ticket that will be put if absent
     * @return Ticket from Map with the same id
     */
    @Override
    public Ticket makeUnique(Map<Integer, Ticket> cache, Ticket ticket) {
        cache.putIfAbsent(ticket.getId(), ticket);
        return cache.get(ticket.getId());
    }
}
