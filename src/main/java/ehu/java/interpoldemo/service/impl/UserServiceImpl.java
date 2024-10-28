package ehu.java.interpoldemo.service.impl;
import ehu.java.interpoldemo.dao.UserDao;
import ehu.java.interpoldemo.dao.impl.UserDaoImpl;
import ehu.java.interpoldemo.model.User;
import ehu.java.interpoldemo.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public boolean authenticate(String login, String password) {
        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }
        String storedHashedPassword = userDao.authenticate(login);
        if (storedHashedPassword == null) {
            return false;
        }
        return BCrypt.checkpw(password, storedHashedPassword);
    }

    public void registerUser(String login, String password) {
        if (login == null || login.isEmpty() ) {
            throw new IllegalArgumentException("Login must be not NULL");
        }
        if (password == null) {
            throw new IllegalArgumentException("Password must be not NULL");
        }
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        User newUser = new User();
        newUser.setLogin(login);
        newUser.setPassword(hashedPassword);
        userDao.saveUser(newUser);
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }
}
