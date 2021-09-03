package ua.project.model.dao.impl;

import ua.project.containers.SQLStatements;
import ua.project.model.dao.UserDao;
import ua.project.model.dao.mapper.UserMapper;
import ua.project.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Illia Koshkin
 */
public class JDBCUserDao implements UserDao {
    private final Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }


    /**
     * This method creates a new User record in database
     * @param entity User object
     * @throws SQLIntegrityConstraintViolationException in case of invalid input
     */
    @Override
    public void create(User entity) throws SQLIntegrityConstraintViolationException {
        try(PreparedStatement ps = connection.prepareCall(SQLStatements.CREATE_USER)) {
            connection.setAutoCommit(false);
            ps.setString(1, entity.getLogin());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getRole().toString());
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
     * This method gets User by id
     * @param id Id of a user
     * @return Optional of User
     */
    @Override
    public Optional<User> findById(int id) {
        Optional<User> user = Optional.empty();
        try(PreparedStatement ps = connection.prepareCall(SQLStatements.FIND_USER_BY_ID)){
            ps.setInt( 1, id);
            ResultSet rs;
            rs = ps.executeQuery();
            UserMapper mapper = new UserMapper();
            if (rs.next()){
                user = Optional.of(mapper.extractFromResultSet(rs));
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return user;
    }

    /**
     * Gets all users
     * @return util.List of User
     */
    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareCall(SQLStatements.FIND_ALL_USERS)){
            ResultSet rs = ps.executeQuery();
            UserMapper mapper = new UserMapper();
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
     * Username of a User
     * @param name String object with username
     * @return Optional of User
     */
    @Override
    public Optional<User> findByName(String name) {

        Optional<User> result = Optional.empty();
        try(PreparedStatement ps = connection.prepareCall(SQLStatements.FIND_USER_BY_NAME)){
            ps.setString( 1, name);
            ResultSet rs;
            rs = ps.executeQuery();
            UserMapper mapper = new UserMapper();
            if (rs.next()){
                result = Optional.of(mapper.extractFromResultSet(rs));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return result;
    }
}
