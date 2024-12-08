package ehu.java.interpoldemo.dao;
import ehu.java.interpoldemo.exception.DaoException;
import ehu.java.interpoldemo.model.Criminal;
import java.util.List;

public interface CriminalDao {
    Criminal findCriminalById(int id) throws DaoException;
    List<Criminal> findAll() throws DaoException;

}
