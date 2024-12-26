package ehu.java.interpoldemo.dao.impl;
import ehu.java.interpoldemo.dao.BaseDao;
import ehu.java.interpoldemo.dao.CriminalDao;
import ehu.java.interpoldemo.dao.connection.ConnectionPool;
import ehu.java.interpoldemo.exception.DaoException;
import ehu.java.interpoldemo.model.Criminal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static ehu.java.interpoldemo.constants.DaoConstant.*;
import static ehu.java.interpoldemo.constants.ParameterNameConstant.*;

public class CriminalDaoImpl extends BaseDao<Criminal> implements CriminalDao {

    private static Logger logger = LogManager.getLogger(CriminalDaoImpl.class);

    @Override
    public Criminal findCriminalById(int id) throws DaoException {
        Criminal criminal = null;
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CRIMINAL_BY_ID)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString(NAME);
                String surname = resultSet.getString(SURNAME);
                LocalDate dateOfBirth = resultSet.getDate(DATE_OF_BIRTH_DAO).toLocalDate();
                String citizenship = resultSet.getString(CITIZENSHIP);
                String description = resultSet.getString(DESCRIPTION);
                double reward = resultSet.getDouble(REWARD);
                boolean isArrested = resultSet.getBoolean(IS_ARRESTED);
                byte[] image = resultSet.getBytes(IMAGE);
                criminal = new Criminal.CriminalBuilder(name, surname).
                        setId(id).
                        setDateOfBirth(dateOfBirth).
                        setCitizenship(citizenship).
                        setDescription(description).
                        setReward(reward).
                        setIsArrested(isArrested).
                        setImage(image).build();
            }
        } catch (SQLException e) {
            logger.error("Error retrieving criminal with ID: " + id, e);
            throw new DaoException("Unable to retrieve criminal. ", e);
        }
        return criminal;
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
                int id = resultSet.getInt(ID);
                String name = resultSet.getString(NAME);
                String surname = resultSet.getString(SURNAME);
                LocalDate dateOfBirth = resultSet.getDate(DATE_OF_BIRTH_DAO).toLocalDate();
                String citizenship = resultSet.getString(CITIZENSHIP);
                String description = resultSet.getString(DESCRIPTION);
                double reward = resultSet.getDouble(REWARD);
                boolean isArrested = resultSet.getBoolean(IS_ARRESTED);
                byte[] image = resultSet.getBytes(IMAGE);
                Criminal criminal = new Criminal.CriminalBuilder(name, surname).
                setId(id).
                setDateOfBirth(dateOfBirth).
                setCitizenship(citizenship).
                setDescription(description).
                setReward(reward).
                setIsArrested(isArrested).
                setImage(image).build();
                criminals.add(criminal);
            }
        } catch (SQLException e) {
            logger.info("Error in database level");
            throw new DaoException("Database error", e);
        }
        return criminals;
    }

    @Override
    protected String getInsertQuery() {
        return INSERT_CRIMINAL;
    }

    @Override
    protected void prepareInsertStatement(PreparedStatement preparedStatement, Criminal criminal) throws SQLException {
        preparedStatement.setString(1, criminal.getName());
        preparedStatement.setString(2, criminal.getSurname());
        preparedStatement.setDate(3, Date.valueOf(criminal.getDateOfBirth()));
        preparedStatement.setString(4, criminal.getCitizenship());
        preparedStatement.setString(5, criminal.getDescription());
        preparedStatement.setDouble(6, criminal.getReward());
        preparedStatement.setBytes(7, criminal.getImage());
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_CRIMINAL;
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, Criminal criminal) throws SQLException {
        preparedStatement.setString(1, criminal.getName());
        preparedStatement.setString(2, criminal.getSurname());
        preparedStatement.setDate(3, Date.valueOf(criminal.getDateOfBirth()));
        preparedStatement.setString(4, criminal.getCitizenship());
        preparedStatement.setString(5, criminal.getDescription());
        preparedStatement.setDouble(6, criminal.getReward());
        preparedStatement.setBytes(7, criminal.getImage());
        preparedStatement.setBoolean(8, criminal.isArrested());
        preparedStatement.setInt(9, criminal.getId());
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_CRIMINAL;
    }

    @Override
    protected void prepareDeleteStatement(PreparedStatement preparedStatement, Criminal criminal) throws SQLException {
        preparedStatement.setInt(1, criminal.getId());
    }
}
