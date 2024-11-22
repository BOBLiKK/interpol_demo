package ehu.java.interpoldemo.dao.impl;
import ehu.java.interpoldemo.dao.UserDao;
import ehu.java.interpoldemo.dao.connection.ConnectionPool;
import ehu.java.interpoldemo.exception.DaoException;
import ehu.java.interpoldemo.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import static ehu.java.interpoldemo.constants.DaoConstant.*;
import static ehu.java.interpoldemo.constants.ParameterNameConstant.*;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
        @Override
    public String authenticate(String login) throws DaoException {
        String storedPassword = null;

        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN)
        ) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                storedPassword = resultSet.getString(PASSWORD);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException("Unable to authenticate user. ", e);
        }
        return storedPassword;
    }

    @Override
    public void saveUser(User user) throws DaoException {
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_USER, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();


            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                } else {
                    throw new DaoException("User created, but ID not generated. ");
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException("Error adding user to the database. ", e);
        }
    }

    @Override
    public User findUserById(int id) throws DaoException {
        User user = null;
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(ID));
                user.setLogin(resultSet.getString(LOGIN));
                user.setPassword(resultSet.getString(PASSWORD));
            }
        } catch (SQLException e) {
            logger.error("Error retrieving user with ID: " + id, e);
            throw new DaoException("Unable to retrieve user. ", e);
        }
        return user;
    }
}
