package ehu.java.interpoldemo.dao.impl;
import ehu.java.interpoldemo.dao.CriminalDao;
import ehu.java.interpoldemo.dao.connection.ConnectionCreator;
import ehu.java.interpoldemo.model.Criminal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static ehu.java.interpoldemo.dao.DaoConstants.*;

public class CriminalDaoImpl implements CriminalDao {
    @Override
    public void saveCriminal(Criminal criminal) {
        try (
                Connection connection = ConnectionCreator.createConnection();
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
            throw new RuntimeException("Error saving criminal to database", e);
        }
    }

    @Override
    public List<Criminal> getAllCriminals() {
        List<Criminal> criminals = new ArrayList<>();
        try (
                Connection connection = ConnectionCreator.createConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_ALL_CRIMINALS)
        ) {
            while (resultSet.next()) {
                Criminal criminal = new Criminal();
                criminal.setId(resultSet.getInt("id"));
                criminal.setName(resultSet.getString("name"));
                criminal.setSurname(resultSet.getString("surname"));
                criminal.setDateOfBirth(resultSet.getDate("dateOfBirth").toLocalDate());
                criminal.setCitizenship(resultSet.getString("citizenship"));
                criminal.setDescription(resultSet.getString("description"));
                criminal.setReward(resultSet.getDouble("reward"));
                criminal.setArrested(resultSet.getBoolean("isArrested"));
                criminals.add(criminal);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving criminals from database", e);
        }
        return criminals;
    }
}
