package ua.project.model.dao.impl;

import ua.project.model.dao.ExhibitionDao;
import ua.project.model.dao.mapper.ExhibitionMapper;
import ua.project.model.dao.mapper.UserMapper;
import ua.project.model.entity.Exhibition;
import ua.project.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;

public class JDBCExhibitionDao implements ExhibitionDao {
    private final Connection connection;

    public JDBCExhibitionDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void create(Exhibition entity) throws SQLIntegrityConstraintViolationException {
//        try(PreparedStatement ps = connection.prepareCall("INSERT INTO users (username, password, email, role)" +
//                " VALUES (?, ?, ?, ?); ")) {
//            connection.setAutoCommit(false);
//            ps.setString(1, entity.getLogin());
//            ps.setString(2, entity.getPassword());
//            ps.setString(3, entity.getEmail());
//            ps.setString(4, entity.getRole().toString());
//            ps.executeUpdate();
//            connection.commit();
//        } catch (SQLIntegrityConstraintViolationException exc) {
//            try {
//                connection.rollback();
//            } catch (SQLException ignored) {
//
//            }
//            throw new SQLIntegrityConstraintViolationException();
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException ignored) {
//
//            }
//        }
    }

    @Override
    public Exhibition findById(int id) {
        return null;
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
}
