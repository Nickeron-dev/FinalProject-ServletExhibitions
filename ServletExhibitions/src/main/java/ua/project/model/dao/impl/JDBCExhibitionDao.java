package ua.project.model.dao.impl;

import ua.project.containers.SQLStatements;
import ua.project.model.dao.ExhibitionDao;
import ua.project.model.dao.mapper.ExhibitionMapper;
import ua.project.model.entity.Exhibition;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Illia Koshkin
 */
public class JDBCExhibitionDao implements ExhibitionDao {
    private final Connection connection;

    public JDBCExhibitionDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * This method creates a new Exhibition record in database
     * @param entity Exhibition object
     * @throws SQLIntegrityConstraintViolationException in case of invalid input
     */
    @Override
    public void create(Exhibition entity) throws SQLIntegrityConstraintViolationException {
        try(PreparedStatement ps = connection.prepareCall(SQLStatements.CREATE_EXHIBITION)) {
            connection.setAutoCommit(false);
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

    /**
     * This method gets Exhibition by id
     * @param id Id of an exhibition
     * @return Optional of Exhibition
     */
    @Override
    public Optional<Exhibition> findById(int id) {
        Optional<Exhibition> exhibition = Optional.empty();
        try(PreparedStatement ps = connection.prepareCall(SQLStatements.FIND_EXHIBITION_BY_ID)){
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

    /**
     * Gets all exhibitions
     * @return util.List of Exhibition
     */
    @Override
    public List<Exhibition> findAll() {
        List<Exhibition> result = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareCall(SQLStatements.FIND_ALL_EXHIBITIONS)){
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

    /**
     * Closes connection
     */
    @Override
    public void close()  {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cancels Exhibition by id
     * @param id Id of an Exhibition
     */
    @Override
    public void cancelById(Integer id) {
        try(PreparedStatement ps = connection.prepareCall(SQLStatements.CANCEL_BY_ID)) {
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

    /**
     * Plans Exhibition by id
     * @param id Id of an Exhibition
     */
    @Override
    public void planById(Integer id) {
        try(PreparedStatement ps = connection.prepareCall(SQLStatements.PLAN_BY_ID)) {
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

    /**
     * Divides all exhibitions by pages and gives certain page
     * @param page Number of a required page
     * @return util.List with all exhibitions from required page
     */
    @Override
    public List<Exhibition> allByPage(Integer page) {
        List<Exhibition> result = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareCall(SQLStatements.FIND_BY_PAGE)) {
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
        return result;
    }

    /**
     * Integer amount of pages
     * @return Integer value with amount of pages
     */
    @Override
    public Integer pagesAvailable() {
        int number = 0;
        try(PreparedStatement ps = connection.prepareCall(SQLStatements.PAGES_AVAILABLE)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                number = rs.getInt(1);
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        number = (int) Math.ceil((double) number / 4);
        return number;
    }

    /**
     * Filters all exhibitions by date
     * @param date LocalDate object that will be used to filter
     * @return util.List of exhibitions
     */
    @Override
    public List<Exhibition> filterByDate(LocalDate date) {
        List<Exhibition> result = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareCall(SQLStatements.FILTER_BY_DATE)){
            ps.setString(1, date.toString());
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
}
