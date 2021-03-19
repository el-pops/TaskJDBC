package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS users "
            + "(id BIGINT NOT NULL AUTO_INCREMENT UNIQUE ,"
            + " userName VARCHAR(99), "
            + " lastName VARCHAR(99), "
            + " age SMALLINT UNSIGNED, "
            + "PRIMARY KEY(id))";
    private final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS users ";
    private final String GET_ALL_USERS_QUERY = "from User";
    private final String CLEAN_TABLE_QUERY = "delete from User ";

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(CREATE_TABLE_QUERY).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Failed to create table. Please try again.");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(DROP_TABLE_QUERY).executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            System.err.println("Failed to drop table. Please try again.");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.println("User " + name + " successfully added");
        } catch (Exception e) {
            System.err.println("Failed to save user. Please try again.");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                transaction.commit();
                System.out.println("User with id " + id + " successfully removed");
            } else
                System.out.println("User with id " + id + " don't exist");
        } catch (Exception e) {
            System.err.println("Failed to remove user. Please try again.");
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery(GET_ALL_USERS_QUERY).list();
        } catch (Exception e) {
            System.err.println("Failed to get users. Please try again.");
            return null;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(CLEAN_TABLE_QUERY).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Failed to clean table. Please try again.");
        }
    }
}
