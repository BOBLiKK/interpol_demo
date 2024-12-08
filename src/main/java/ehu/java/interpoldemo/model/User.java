package ehu.java.interpoldemo.model;
import java.util.Objects;
import java.util.StringJoiner;

public class User extends AbstractModel{


    private User(UserBuilder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.password = builder.password;
        this.email = builder.email;
        this.userRole = builder.userRole;
    }


    private int id;
    private String login;
    private String password;
    private String email;
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
    public String getEmail() {return email;}
    public UserRole getUserRole() {
        return userRole;
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


    public static class UserBuilder {

        //todo
        private int id;
        private String login;
        private String password;
        private String email;
        private UserRole userRole = UserRole.getDefaultRole();

        public UserBuilder(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public UserBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setUserRole(UserRole userRole) {
            this.userRole = userRole;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }
}



