package ua.project.model.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;

/**
 * @author Illia Koshkin
 */
public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;
    static Logger logger = LogManager.getLogger(ConnectionPoolHolder.class);

    /**
     * This method provides a connection to a MySQL database
     * @return DataSource object with connection
     */
    public static DataSource getDataSource(){

        if (dataSource == null){
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl("jdbc:mysql://localhost:3306/servletexhibitions?useUnicode=yes&characterEncoding=UTF-8");
                    ds.setUsername("root");
                    ds.setPassword("trumpet");
                    ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                    logger.info("Connected to a database");
                }
            }
        }
        return dataSource;

    }
}
