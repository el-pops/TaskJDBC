package jm.task.core.jdbc.dao;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final String CREATE_TABLE_QUERY =
            "CREATE TABLE IF NOT EXISTS users "
                    + "(id BIGINT NOT NULL AUTO_INCREMENT UNIQUE ,"
                    + " userName VARCHAR(99), "
                    + " lastName VARCHAR(99), "
                    + " age SMALLINT UNSIGNED, "
                    + "PRIMARY KEY(id));";
    private final String DROP_TABLE_QUERY =
            "DROP TABLE IF EXISTS users ";

    private final String GET_ALL_USERS_QUERY =
            "SELECT * FROM users";
    private final String ADD_USER_QUERY =
            "INSERT INTO users VALUES(firstName, lastName, age) (default ,?,?,?)";
    private final String REMOVE_USER_QUERY =
            "DELETE FROM users WHERE id = ?";
    private final String CLEAN_USERS_QUERY =
            "DELETE FROM users";

    public UserDaoJDBCImpl() {
    }


    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(DROP_TABLE_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_QUERY)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            System.out.println("User " + name + " successfully added");
        } catch (MysqlDataTruncation e) {
            System.out.println("Failed to add user " + name + ". Please enter correct data.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER_QUERY)) {
            preparedStatement.setLong(1, id);
             int changes = preparedStatement.executeUpdate();
             if (changes == 1){
                 System.out.println("User with id " + id + " successfully removed");
             } else {
                 System.out.println("User with id " + id + " don't exist");
             }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS_QUERY);
            while (resultSet.next()) {
                User user = new User(resultSet.getString("userName"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(CLEAN_USERS_QUERY);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
