package ua.project.model.dao.impl;

import ua.project.model.dao.DaoFactory;
import ua.project.model.dao.ExhibitionDao;
import ua.project.model.dao.TicketDao;
import ua.project.model.dao.UserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Illia Koshkin
 */
public class JDBCDaoFactory extends DaoFactory {
    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public ExhibitionDao createExhibitionDao() {
        return new JDBCExhibitionDao(getConnection());
    }

    @Override
    public TicketDao createTicketDao() {
        return new JDBCTicketDao(getConnection());
    }


    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
