package ehu.java.interpoldemo.dao.impl;
import ehu.java.interpoldemo.dao.BaseDao;
import ehu.java.interpoldemo.dao.CriminalDao;
import ehu.java.interpoldemo.dao.connection.ConnectionPool;
import ehu.java.interpoldemo.exception.DaoException;
import ehu.java.interpoldemo.model.AbstractModel;
import ehu.java.interpoldemo.model.Criminal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static ehu.java.interpoldemo.constants.DaoConstant.*;
import static ehu.java.interpoldemo.constants.ParameterNameConstant.*;

public class CriminalDaoImpl extends BaseDao implements CriminalDao {
    @Override
    public void saveCriminal(Criminal criminal) {
        try (
                Connection connection =  ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CRIMINAL)
        ) {
            preparedStatement.setString(1, criminal.getName());
            preparedStatement.setString(2, criminal.getSurname());
            preparedStatement.setDate(3, Date.valueOf(criminal.getDateOfBirth()));
            preparedStatement.setString(4, criminal.getCitizenship());
            preparedStatement.setString(5, criminal.getDescription());
            preparedStatement.setDouble(6, criminal.getReward());
            preparedStatement.setBoolean(7, criminal.isArrested());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }


    //todo

    @Override
    public boolean insert(AbstractModel model) throws DaoException {
        return false;
    }

    @Override
    public boolean update(AbstractModel model) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(AbstractModel model) throws DaoException {
        return false;
    }

    @Override
    public List findAll() throws DaoException {
        List<Criminal> criminals = new ArrayList<>();
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_ALL_CRIMINALS)
        ) {
            while (resultSet.next()) {
                Criminal criminal = new Criminal();
                criminal.setId(resultSet.getInt(ID));
                criminal.setName(resultSet.getString(NAME));
                criminal.setSurname(resultSet.getString(SURNAME));
                criminal.setDateOfBirth(resultSet.getDate(DATE_OF_BIRTH).toLocalDate());
                criminal.setCitizenship(resultSet.getString(CITIZENSHIP));
                criminal.setDescription(resultSet.getString(DESCRIPTION));
                criminal.setReward(resultSet.getDouble(REWARD));
                criminal.setArrested(resultSet.getBoolean(IS_ARRESTED));
                criminals.add(criminal);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
        return criminals;
    }
}
