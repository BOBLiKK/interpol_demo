package ehu.java.interpoldemo.dao.impl;
import ehu.java.interpoldemo.dao.UserDao;
import ehu.java.interpoldemo.dao.connection.ConnectionCreator;
import ehu.java.interpoldemo.model.User;
import java.sql.*;
import static ehu.java.interpoldemo.dao.DaoConstants.*;

public class UserDaoImpl implements UserDao {
        @Override
    public String authenticate(String login) {
        String storedPassword = null;

        try (
                Connection connection = ConnectionCreator.createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN)
        ) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                storedPassword = resultSet.getString("password");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving user by login", e);
        }
        return storedPassword;
    }

    @Override
    public void saveUser(User user) {
        try (
                Connection connection = ConnectionCreator.createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_USER, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword()); // hash password saved
            preparedStatement.executeUpdate();

            // getting generated id
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving new user", e);
        }
    }

    @Override
    public User getUserById(int id) {
        User user = null;
        try (
                Connection connection = ConnectionCreator.createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving user by ID", e);
        }
        return user;
    }
}
