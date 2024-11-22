package ehu.java.interpoldemo.service;

import ehu.java.interpoldemo.exception.ServiceException;

public interface UserService {
    boolean authenticate (String login, String password) throws ServiceException;

    boolean registerUser(String login, String password) throws ServiceException;
}
