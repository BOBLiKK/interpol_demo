package ehu.java.interpoldemo.dao;

import ehu.java.interpoldemo.dao.connection.ConnectionPool;
import ehu.java.interpoldemo.model.AbstractModel;
import ehu.java.interpoldemo.exception.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public abstract class BaseDao <T extends AbstractModel>{

    protected abstract String getInsertQuery();
    protected abstract void prepareInsertStatement(PreparedStatement preparedStatement, T model) throws SQLException;
    public boolean insert(T model) throws DaoException{
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery())
        ) {
            prepareInsertStatement(preparedStatement, model);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error updating the model.", e);
        }
    }

    protected abstract String getUpdateQuery();
    protected abstract void prepareUpdateStatement(PreparedStatement preparedStatement, T model) throws SQLException;

    public boolean update(T model) throws DaoException{
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(getUpdateQuery())
        ) {
            prepareUpdateStatement(preparedStatement, model);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error updating the model.", e);
        }
    }

    protected abstract String getDeleteQuery();
    protected abstract void prepareDeleteStatement(PreparedStatement preparedStatement, T model) throws SQLException;

    public  boolean delete(T model) throws DaoException{
        try(
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery())
        ){
            prepareDeleteStatement(preparedStatement, model);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error deleting the model.", e);
        }
    }

    public abstract List<T> findAll() throws DaoException;
}
