package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static Connection connection;
    private final static String URL = "jdbc:mysql://localhost:3306/jm";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String DIAlECT = "org.hibernate.dialect.MySQL5Dialect";
    private final static String HBM2DDL_AUTO = "update";

    private static final SessionFactory sessionFactory = buildSessionFactory();

    public static Connection getConnection() throws SQLException {
        {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            return connection;
        }
    }

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            Properties properties = new Properties();
            properties.put(Environment.DRIVER, DRIVER);
            properties.put(Environment.URL, URL);
            properties.put(Environment.USER, USERNAME);
            properties.put(Environment.PASS, PASSWORD);
            properties.put(Environment.DIALECT, DIAlECT);
            properties.put(Environment.HBM2DDL_AUTO, HBM2DDL_AUTO);

            configuration.addAnnotatedClass(User.class);
            configuration.setProperties(properties);

            SessionFactory newSessionFactory = configuration.buildSessionFactory(new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build());
            return newSessionFactory;
        } catch (Exception e) {
            System.err.println("Failed to create session factory.");
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
