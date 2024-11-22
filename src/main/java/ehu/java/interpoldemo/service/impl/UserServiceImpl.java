package ehu.java.interpoldemo.service.impl;
import ehu.java.interpoldemo.controller.Controller;
import ehu.java.interpoldemo.dao.UserDao;
import ehu.java.interpoldemo.dao.impl.UserDaoImpl;
import ehu.java.interpoldemo.exception.DaoException;
import ehu.java.interpoldemo.exception.ServiceException;
import ehu.java.interpoldemo.model.User;
import ehu.java.interpoldemo.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import ehu.java.interpoldemo.validator.impl.*;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();
    private static final Logger logger = LogManager.getLogger(Controller.class);


    //todo
    @Override
    public boolean authenticate(String login, String password) throws ServiceException {

        try {
            checkInput(login, password);
            String storedHashedPassword = userDao.authenticate(login);
            if (storedHashedPassword == null) {
                return false;
            }
            return BCrypt.checkpw(password, storedHashedPassword);

        } catch (DaoException e) {
            logger.error("Error during authentication for login {}: {}", login, e.getMessage(), e);
            throw new ServiceException("Authentication error. Please try again later.");
        }
    }

    @Override
    public boolean registerUser(String login, String password) throws ServiceException {

        ValidatorImpl validator = new ValidatorImpl();
        if (!validator.isValidLogin(login) || !validator.isValidPassword(password)) {
            return false;
        }

        try {
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            User newUser = new User();
            newUser.setLogin(login);
            newUser.setPassword(hashedPassword);
            userDao.saveUser(newUser);
            return true;

        } catch (DaoException e) {
            logger.error("Error during user registration for login {}: {}", login, e.getMessage(), e);
            throw new ServiceException("Registration error. Please try again later.");
        }
    }

    private void checkInput(String... inputs) {
        for (String input : inputs) {
            if (input == null || input.isEmpty()) {
                throw new IllegalArgumentException("Input cannot be null or empty");
            }
        }
    }

    public User findUserById(int id) throws ServiceException {
        try {
            return userDao.findUserById(id);

        } catch (DaoException e) {
            logger.error("Error retrieving user by ID {}: {}", id, e.getMessage(), e);
            throw new ServiceException("User retrieval error. Please try again later.");
        }
    }


}
