package ua.project.model.dao.impl;

import ua.project.containers.SQLStatements;
import ua.project.model.dao.TicketDao;
import ua.project.model.dao.mapper.TicketMapper;
import ua.project.model.entity.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Illia Koshkin
 */
public class JDBCTicketDao implements TicketDao {
    private final Connection connection;

    public JDBCTicketDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * This method creates a new Ticket record in database
     * @param entity Ticket object
     * @throws SQLIntegrityConstraintViolationException in case of invalid values given to SQL
     */
    @Override
    public void create(Ticket entity) throws SQLIntegrityConstraintViolationException {
        try(PreparedStatement ps = connection.prepareCall(SQLStatements.CREATE_TICKET)) {
            connection.setAutoCommit(false);
            ps.setString(1, entity.getExhibitionTopic());
            ps.setInt(2, entity.getExhibitionId());
            ps.setString(3, entity.getUserEmail());
            ps.setInt(4, entity.getUserId());
            ps.executeUpdate();
            connection.commit();
        } catch (SQLIntegrityConstraintViolationException exc) {
            try {
                connection.rollback();
            } catch (SQLException ignored) {

            }
            throw new SQLIntegrityConstraintViolationException();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignored) {

            }
        }
    }

    /**
     * This method gets Ticket by id
     * @param id Id of a ticket
     * @return Optional of Ticket
     */
    @Override
    public Optional<Ticket> findById(int id) {
        Optional<Ticket> ticket = Optional.empty();
        try(PreparedStatement ps = connection.prepareCall(SQLStatements.FIND_TICKET_BY_ID)){
            ps.setInt( 1, id);
            ResultSet rs;
            rs = ps.executeQuery();
            TicketMapper mapper = new TicketMapper();
            if (rs.next()){
                ticket = Optional.of(mapper.extractFromResultSet(rs));
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return ticket;
    }

    /**
     * Gets all tickets
     * @return util.List of Ticket
     */
    @Override
    public List<Ticket> findAll() {
        List<Ticket> result = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareCall(SQLStatements.FIND_ALL_TICKETS)){
            ResultSet rs = ps.executeQuery();
            TicketMapper mapper = new TicketMapper();
            while (rs.next()) {
                result.add(mapper.extractFromResultSet(rs));
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    /**
     * Closes connection
     */
    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Counts all tickets to an Exhibition by id
     * @param exhibitionId Id of an exhibition
     * @return amount of tickets in type long
     */
    @Override
    public long countByExhibitionId(Integer exhibitionId) {
        long amount = 0;
        try(PreparedStatement ps = connection.prepareCall(SQLStatements.COUNT_BY_EXHIBITION_ID)){
            ps.setInt( 1, exhibitionId);
            ResultSet rs;
            rs = ps.executeQuery();
            if (rs.next()){
                amount = rs.getInt(1);
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return amount;
    }
}
