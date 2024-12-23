package ehu.java.interpoldemo.dao.impl;

import ehu.java.interpoldemo.dao.BaseDao;
import ehu.java.interpoldemo.dao.RequestDao;
import ehu.java.interpoldemo.dao.connection.ConnectionPool;
import ehu.java.interpoldemo.exception.DaoException;
import ehu.java.interpoldemo.model.Criminal;
import ehu.java.interpoldemo.model.Request;
import ehu.java.interpoldemo.model.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ehu.java.interpoldemo.constants.DaoConstant.*;
import static ehu.java.interpoldemo.constants.ParameterNameConstant.*;

public class RequestDaoImpl extends BaseDao<Request> implements RequestDao {

    private static final Logger logger = LogManager.getLogger(RequestDaoImpl.class);

    @Override
    protected String getInsertQuery() {
        return INSERT_REQUEST;
    }

    @Override
    protected void prepareInsertStatement(PreparedStatement preparedStatement, Request request) throws SQLException {
        preparedStatement.setInt(1, request.getUserId());
        preparedStatement.setString(2, request.getCriminal().getName());
        preparedStatement.setString(3, request.getCriminal().getSurname());
        preparedStatement.setDate(4, Date.valueOf(request.getCriminal().getDateOfBirth()));
        preparedStatement.setString(5, request.getCriminal().getCitizenship());
        preparedStatement.setString(6, request.getCriminal().getDescription());
        preparedStatement.setDouble(7, request.getCriminal().getReward());
        preparedStatement.setString(8, request.getComment());
        preparedStatement.setString(9, request.getStatus().getStatus());
    }


    @Override
    public boolean updateStatus(int requestId, String status) throws DaoException {
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REQUEST_STATUS)
        ) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, requestId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error updating request status", e);
        }
    }

    @Override
    public Request findById(int requestId) throws DaoException {
        Request request = null;
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REQUEST_BY_ID)
        ) {
            preparedStatement.setInt(1, requestId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                request = extractRequestFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Error finding request by ID", e);
        }
        return request;
    }

    @Override
    public List<Request> findByUserId(int userId) throws DaoException {
        List<Request> requests = new ArrayList<>();
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REQUEST_BY_USER_ID)
        ) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                requests.add(extractRequestFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("Error finding requests by user ID", e);
        }
        return requests;
    }

    @Override
    public List<Request> findAll() throws DaoException {
        List<Request> requests = new ArrayList<>();
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_ALL_REQUESTS)
        ) {
            while (resultSet.next()) {
                requests.add(extractRequestFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("Error finding all requests", e);
        }
        return requests;
    }

    private Request extractRequestFromResultSet(ResultSet resultSet) throws SQLException {
        int requestId = resultSet.getInt(REQUEST_ID);
        int userId = resultSet.getInt(USER_ID);
        String name = resultSet.getString(NAME);
        String surname = resultSet.getString(SURNAME);
        Date dateOfBirth = resultSet.getDate(DATE_OF_BIRTH_DAO);
        String citizenship = resultSet.getString(CITIZENSHIP);
        String description = resultSet.getString(DESCRIPTION);
        double reward = resultSet.getDouble(REWARD);
        String comments = resultSet.getString(COMMENT);
        Status status = Status.valueOf(resultSet.getString(STATUS));

        Criminal criminal = new Criminal.CriminalBuilder(name, surname)
                .setDateOfBirth(dateOfBirth.toLocalDate())
                .setCitizenship(citizenship)
                .setDescription(description)
                .setReward(reward)
                .build();

        return new Request.RequestBuilder()
                .setRequestId(requestId)
                .setUserId(userId)
                .setCriminal(criminal)
                .setComment(comments)
                .setStatus(status)
                .build();
    }


    //todo

    @Override
    protected String getUpdateQuery() {
        return "";
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, Request model) throws SQLException {

    }

    @Override
    protected String getDeleteQuery() {
        return "";
    }

    @Override
    protected void prepareDeleteStatement(PreparedStatement preparedStatement, Request model) throws SQLException {

    }
}
