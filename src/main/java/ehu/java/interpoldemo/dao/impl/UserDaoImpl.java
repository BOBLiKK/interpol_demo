package ehu.java.interpoldemo.dao.impl;
import ehu.java.interpoldemo.dao.BaseDao;
import ehu.java.interpoldemo.dao.UserDao;
import ehu.java.interpoldemo.dao.connection.ConnectionPool;
import ehu.java.interpoldemo.exception.DaoException;
import ehu.java.interpoldemo.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;

import static ehu.java.interpoldemo.constants.DaoConstant.*;
import static ehu.java.interpoldemo.constants.ParameterNameConstant.*;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

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
    public User findUserById(int id) throws DaoException {
        User user = null;
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int user_id = resultSet.getInt(ID);
                String login = resultSet.getString(LOGIN);
                String password = resultSet.getString(PASSWORD);
                user = new User.UserBuilder(login, password).setId(user_id).build();
            }
        } catch (SQLException e) {
            logger.error("Error retrieving user with ID: " + id, e);
            throw new DaoException("Unable to retrieve user. ", e);
        }
        return user;
    }

    @Override
    public User findUserByLogin(String login) throws DaoException {
        User user = null;
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN)
        ) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String password = resultSet.getString(PASSWORD);
                user = new User.UserBuilder(login, password).build();
            }
        } catch (SQLException e) {
            logger.error("Error finding user by login: " + login, e);
            throw new DaoException("Unable to find user by login.", e);
        }
        return user;
    }

    @Override
    public String findRoleByLogin(String login) throws DaoException {
        String role = null;
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROLE_BY_LOGIN)
        ) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = resultSet.getString(ROLE);
            }
        } catch (SQLException e) {
            throw new DaoException("Error fetching user role from database.", e);
        }
        return role;
    }

    @Override
    public int findUserIdByLogin(String login) throws DaoException {
        int userId = 0;
        try(
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement  preparedStatement = connection.prepareStatement(SELECT_USER_ID_BY_LOGIN)
        ){
            preparedStatement.setString(1,login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt(ID);
            }
        } catch (SQLException e) {
            throw new DaoException("Error finding user id from database.", e);
        }
        return userId;
    }

    @Override
    protected String getInsertQuery() {
        return INSERT_NEW_USER_WITH_LOGIN;
    }

    @Override
    protected void prepareInsertStatement(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_USER;
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(2, user.getLogin());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setInt(4, user.getId());
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_USER;
    }

    @Override
    protected void prepareDeleteStatement(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setInt(1, user.getId());
    }
}
