package ehu.java.interpoldemo.dao;

import ehu.java.interpoldemo.exception.DaoException;
import ehu.java.interpoldemo.model.User;

public interface UserDao {
    String authenticate(String login) throws DaoException;
    void saveUser(User user) throws DaoException;
    User findUserById(int id) throws DaoException;
}
