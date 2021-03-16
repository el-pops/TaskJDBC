package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("user1", "1", (byte) 123);
        userDaoJDBC.saveUser("user2", "2", (byte) 12);
        userDaoJDBC.saveUser("user3", "3", (byte) 125);
        userDaoJDBC.saveUser("user4", "4", (byte) 125);
        System.out.println(userDaoJDBC.getAllUsers());
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
    }
}
