package ehu.java.interpoldemo.dao;
import ehu.java.interpoldemo.exception.DaoException;
import ehu.java.interpoldemo.model.User;

public interface UserDao {
    String authenticate(String login) throws DaoException;
    User findUserById(int id) throws DaoException;
    User findUserByLogin(String login) throws DaoException;
    String findRoleByLogin(String login) throws DaoException;
    boolean update(User user) throws DaoException;
}
