package ua.project.model.dao.impl;

import ua.project.model.dao.ExhibitionDao;
import ua.project.model.dao.mapper.ExhibitionMapper;
import ua.project.model.dao.mapper.UserMapper;
import ua.project.model.entity.Exhibition;
import ua.project.model.entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCExhibitionDao implements ExhibitionDao {
    private final Connection connection;

    public JDBCExhibitionDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void create(Exhibition entity) throws SQLIntegrityConstraintViolationException {
        try(PreparedStatement ps = connection.prepareCall("INSERT INTO exhibitions (topic, startDate, endDate, startTime, endTime, rooms, price, state)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            connection.setAutoCommit(false);
            System.out.println(entity.getTopic());
            ps.setString(1, entity.getTopic());
            ps.setString(2, entity.getStartDate().toString());
            ps.setString(3, entity.getEndDate().toString());
            ps.setString(4, entity.getStartTimeEveryDay().toString());
            ps.setString(5, entity.getEndTimeEveryDay().toString());
            ps.setString(6, entity.getRooms().toString());
            ps.setString(7, entity.getPrice().toString());
            ps.setString(8, entity.getState().toString());
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
    public Optional<Exhibition> findById(int id) {
        Optional<Exhibition> exhibition = Optional.empty();
        try(PreparedStatement ps = connection.prepareCall("SELECT * FROM exhibitions WHERE id = ?")){
            ps.setInt( 1, id);
            ResultSet rs;
            rs = ps.executeQuery();
            ExhibitionMapper mapper = new ExhibitionMapper();
            if (rs.next()){
                exhibition = Optional.of(mapper.extractFromResultSet(rs));
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return exhibition;
    }



    @Override
    public List<Exhibition> findAll() {
        List<Exhibition> result = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareCall("SELECT * FROM exhibitions")){
            ResultSet rs = ps.executeQuery();
            ExhibitionMapper mapper = new ExhibitionMapper();
            while (rs.next()) {
                result.add(mapper.extractFromResultSet(rs));
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public void update(Exhibition entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close()  {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cancelById(Integer id) {
        try(PreparedStatement ps = connection.prepareCall("UPDATE exhibitions SET state = 'CANCELED' WHERE (id = ?)")) {
            connection.setAutoCommit(false);
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignored) {

            }
        }
    }

    @Override
    public void planById(Integer id) {
        try(PreparedStatement ps = connection.prepareCall("UPDATE exhibitions SET state = 'PLANNED' WHERE (id = ?)")) {
            connection.setAutoCommit(false);
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignored) {

            }
        }
    }

    @Override
    public List<Exhibition> allByPage(Integer page) {
        List<Exhibition> result = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareCall("SELECT * FROM exhibitions LIMIT ?,?")) {
            ps.setInt(1, (page - 1) * 4);
            ps.setInt(2, 4);
            ResultSet rs = ps.executeQuery();
            ExhibitionMapper mapper = new ExhibitionMapper();
            while (rs.next()) {
                result.add(mapper.extractFromResultSet(rs));
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        System.out.println(result);
        return result;
    }

    @Override
    public Integer pagesAvailable() {
        Integer number = 0;
        try(PreparedStatement ps = connection.prepareCall("SELECT COUNT(*) FROM exhibitions")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                number = rs.getInt(1);
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        number = (int) Math.ceil((double) number / 4);
        System.out.println("Total pages: " + number);
        return number;
    }

    @Override
    public List<Exhibition> filterByDate(LocalDate date) {
        List<Exhibition> result = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareCall("SELECT * FROM exhibitions WHERE endDate >= ?")){
            ps.setString(1, date.toString());
            ResultSet rs = ps.executeQuery();
            ExhibitionMapper mapper = new ExhibitionMapper();
            while (rs.next()) {
                result.add(mapper.extractFromResultSet(rs));
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        System.out.println(result);
        return result;
    }
}
