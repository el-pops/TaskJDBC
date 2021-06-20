package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            UserService userService = new UserServiceImpl();
            userService.createUsersTable();
            userService.saveUser("user1", "1", (byte) 123);
            userService.saveUser("user2", "2", (byte) 12);
            userService.saveUser("user3", "3", (byte) 125);
            userService.saveUser("user4", "4", (byte) 125);
            userService.removeUserById(2);
            System.out.println(userService.getAllUsers());
            userService.cleanUsersTable();
            System.out.println(userService.getAllUsers());
            userService.dropUsersTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
