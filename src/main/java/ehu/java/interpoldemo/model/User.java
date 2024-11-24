package ehu.java.interpoldemo.model;
import java.util.Objects;
import java.util.StringJoiner;

public class User extends AbstractModel{

    public User() {
    }

    public User(int id, String login, String password, UserRole  userRole) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.userRole = userRole;
    }

    public User(String login, String password, UserRole  userRole) {
        this.login = login;
        this.password = password;
        this.userRole = userRole;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }


    private int id;
    private String login;
    private String password;
    private UserRole userRole;

    public int getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login) && Objects.equals(password, user.password) && userRole == user.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("login='" + login + "'")
                .add("password='" + password + "'")
                .add("userRole=" + userRole)
                .toString();
    }
}
