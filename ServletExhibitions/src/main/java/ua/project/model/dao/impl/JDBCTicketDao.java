package ua.project.model.dao.impl;

import ua.project.model.dao.TicketDao;
import ua.project.model.dao.mapper.UserMapper;
import ua.project.model.entity.Ticket;
import ua.project.model.entity.User;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class JDBCTicketDao implements TicketDao {
    private final Connection connection;

    public JDBCTicketDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Ticket entity) throws SQLIntegrityConstraintViolationException {
        try(PreparedStatement ps = connection.prepareCall("INSERT INTO tickets (exhibitionTopic, exhibitionId, userEmail, userId)" +
                " VALUES (?, ?, ?, ?); ")) {
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

    @Override
    public Optional<Ticket> findById(int id) {
        return null;
    }

    @Override
    public List<Ticket> findAll() {
        return null;
    }

    @Override
    public void update(Ticket entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }

    @Override
    public long countByExhibitionId(Integer exhibitionId) {
        long amount = 0;
        try(PreparedStatement ps = connection.prepareCall("SELECT COUNT(*) FROM tickets WHERE exhibitionId = ?")){
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
