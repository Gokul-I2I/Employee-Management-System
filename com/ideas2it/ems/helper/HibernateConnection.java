package com.ideas2it.ems.helper;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ideas2it.ems.exception.MyException;

/**
 * This class connect the DataBase
 * @author Gokul
 */
public class HibernateConnection {

    /**
     *  To connect the database
     * @throws MyException
     */
    public static Session connection() throws MyException {
        Session session = null;
        try {
            Configuration configuration = new Configuration();
            configuration.configure("Hibernate.cfg.xml");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            session = sessionFactory.openSession();
        } catch (Exception e) {
            throw new MyException("Initial SessionFactory creation failed.", e);
        }
        return session;
    }
}
