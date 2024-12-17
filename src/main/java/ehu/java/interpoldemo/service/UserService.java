package ehu.java.interpoldemo.service;

import ehu.java.interpoldemo.exception.ServiceException;
import ehu.java.interpoldemo.model.User;

public interface UserService {
    boolean authenticate (String login, String password) throws ServiceException;
    boolean registerUser(String login,String password) throws ServiceException;
    boolean registerUserWithEmail(String email, String password) throws ServiceException;
    boolean sendEmail(String email) throws ServiceException;
    boolean deleteUser(int id) throws ServiceException;
    boolean checkIfExists(String login) throws ServiceException;
    int findUserId(String login) throws ServiceException;
    String findRole(String login) throws ServiceException;
    User findUserById(int id) throws ServiceException;

}
