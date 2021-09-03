package ua.project.model.dao;

import ua.project.model.dao.impl.JDBCDaoFactory;

/**
 * @author Illia Koshkin
 */
public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    /**
     * This method creates UserDao using DataSource
     * @return UserDao object
     */
    public abstract UserDao createUserDao();

    /**
     * This method creates ExhibitionDao using DataSource
     * @return ExhibitionDao object
     */
    public abstract ExhibitionDao createExhibitionDao();

    /**
     * This method creates TicketDao using DataSource
     * @return TicketDao object
     */
    public abstract TicketDao createTicketDao();

    /**
     * This method gives JDBCDaoFactory
     * @return JDBCDaoFactory object
     */
    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
