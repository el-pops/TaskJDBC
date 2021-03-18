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
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            return connection;
        }
    }

}
