package ehu.java.interpoldemo.service.impl;

import ehu.java.interpoldemo.dao.UserDao;
import ehu.java.interpoldemo.dao.impl.UserDaoImpl;
import ehu.java.interpoldemo.email.EmailSender;
import ehu.java.interpoldemo.exception.DaoException;
import ehu.java.interpoldemo.exception.ServiceException;
import ehu.java.interpoldemo.model.User;
import ehu.java.interpoldemo.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static ehu.java.interpoldemo.constants.PageNameConstant.MAIL_PROPERTIES;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();
    private final UserDaoImpl userDaoImpl = new UserDaoImpl();
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

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
        try {
            checkInput(login, password);
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            User newUser = new User.UserBuilder(login, hashedPassword).build();
            userDaoImpl.insert(newUser);
            return true;
        } catch (DaoException e) {
            logger.error("Error during user registration for login {}: {}", login, e.getMessage(), e);
            throw new ServiceException("Registration error. Please try again later.");
        }
    }

    @Override
    public boolean registerUserWithEmail(String email, String password) throws ServiceException {
        try {
            checkInput(email, password);
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            User newUser = new User.UserBuilder(email, hashedPassword).build();
            if(!sendEmail(email)) {
                logger.info("Error sending email. ");
            }
            return userDaoImpl.insert(newUser);
        } catch (DaoException e) {
            logger.error("Error during user registration for email {}: {}", email, e.getMessage(), e);
            throw new ServiceException("Registration error.");
        }
    }

    @Override
    public boolean sendEmail(String email) {
        String subject = "Welcome email test.";
        String messageText = "Welcome to Interpol Demo.";
        Properties props = new Properties();
        try (InputStream input = UserServiceImpl.class.getClassLoader().getResourceAsStream(MAIL_PROPERTIES)) {
            if (input != null) {
                props.load(input);
            } else {
                logger.error("Mail properties file not found.");
                return false;
            }
            EmailSender emailSender = new EmailSender(email, subject, messageText, props);
            emailSender.send();
            logger.info("Email sent successfully to: " + email);
        } catch (IOException e) {
            logger.error("Error loading mail properties file: " + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean deleteUser(int id) throws ServiceException {
        boolean deletedSuccessfully = false;
        try{
            User user = findUserById(id);
            deletedSuccessfully = userDaoImpl.delete(user);
        } catch(DaoException e){
            logger.error("Error deleting criminal on service level");
            throw new ServiceException(e);
        }
        return deletedSuccessfully;
    }

    @Override
    public boolean checkIfExists(String login) throws ServiceException  {
        boolean exists = false;
        try{
            if (userDao.findUserByLogin(login) != null) {
                exists = true;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return exists;
    }

    @Override
    public int findUserId(String login) throws ServiceException {
        try{
            User user = userDao.findUserByLogin(login);
            return user.getId();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public String findRole(String login) throws ServiceException {
        try{
            return userDao.findRoleByLogin(login);
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public User findUserById(int id) throws ServiceException {
        try{
            return userDao.findUserById(id);
        }catch(DaoException e){
            throw new ServiceException(e);
        }
    }

    private void checkInput(String... inputs) {
        for (String input : inputs) {
            if (input == null || input.isEmpty()) {
                throw new IllegalArgumentException("Input cannot be null or empty");
            }
        }
    }
}
