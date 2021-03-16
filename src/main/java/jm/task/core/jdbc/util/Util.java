package jm.task.core.jdbc.util;

//import com.mysql.fabric.jdbc.FabricMySQLDriver;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static Connection connection;
    private static Driver driver;
    private final static String URL = "jdbc:mysql://localhost:3306/jm";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";


    public static Connection getConnection() throws SQLException {
        {
//                driver = new com.mysql.cj.jdbc.Driver();
//                DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            System.out.println("Successfully connected to database");

            return connection;
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection successfully closed");
        } catch (SQLException e) {
            System.out.println("Can't close connection");
            ;
        }
    }
}
