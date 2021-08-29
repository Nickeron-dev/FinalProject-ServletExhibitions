package ua.project.model.dao.mapper;

import ua.project.model.entity.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TicketMapper implements ObjectMapper<Ticket> {
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

    @Override
    public Ticket makeUnique(Map<Integer, Ticket> cache, Ticket ticket) {
        cache.putIfAbsent(ticket.getId(), ticket);
        return cache.get(ticket.getId());
    }
}
