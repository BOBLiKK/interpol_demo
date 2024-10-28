package ehu.java.interpoldemo.dao;

import ehu.java.interpoldemo.model.User;

public interface UserDao {
    String authenticate(String login);
    void saveUser(User user);
    User getUserById(int id);
}
