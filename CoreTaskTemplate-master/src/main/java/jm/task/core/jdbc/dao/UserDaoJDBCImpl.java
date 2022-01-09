package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.sql.Insert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Statement statement = Util.connection.createStatement();
            String SQL = "CREATE TABLE IF NOT EXISTS user " +
                    "(id       bigint not null AUTO_INCREMENT, " +
                    " name     varchar(30), " +
                    " lastName varchar(30), " +
                    " age      tinyint, " +
                    " PRIMARY KEY (id))";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try {
            Statement statement = Util.connection.createStatement();
                statement.executeUpdate("drop table if exists user");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement =
                    Util.connection.prepareStatement("Insert into user(name,lastName,age) values(?,?,?)")){
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement =
                    Util.connection.prepareStatement("delete from user where id=?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<User> getAllUsers() {
        List<User> people = new ArrayList<>();
        try {
            Statement statement = Util.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                people.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = Util.connection.createStatement();
            statement.executeUpdate("truncate table user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
