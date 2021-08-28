package ua.project.model.dao.impl;

import ua.project.model.dao.UserDao;
import ua.project.model.dao.mapper.UserMapper;
import ua.project.model.entity.User;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class JDBCUserDao implements UserDao {
    private final Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void create(User entity) throws SQLIntegrityConstraintViolationException {
        try(PreparedStatement ps = connection.prepareCall("INSERT INTO users (username, password, email, role)" +
                " VALUES (?, ?, ?, ?); ")) {
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

    @Override
    public User findById(int id) {
        return null;
    }



    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

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
    public Optional<User> findByName(String name) {

        Optional<User> result = Optional.empty();
        try(PreparedStatement ps = connection.prepareCall("SELECT * FROM users WHERE username = ?")){
            ps.setString( 1, name);
            ResultSet rs;
            rs = ps.executeQuery();
            UserMapper mapper = new UserMapper();
            if (rs.next()){
                result = Optional.of(mapper.extractFromResultSet(rs));
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return result;
    }
}
