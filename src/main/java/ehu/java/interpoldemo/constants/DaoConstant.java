package ehu.java.interpoldemo.constants;

public final class DaoConstant {

    private DaoConstant() {
    }

    //select queries
    public static final String SELECT_ALL_CRIMINALS = "SELECT * FROM criminals";
    public static final String SELECT_USER_BY_LOGIN = "SELECT password FROM users WHERE login = ?";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    public static final String SELECT_ROLE_BY_LOGIN = "SELECT role FROM users WHERE login = ?";
    public static final String SELECT_CRIMINAL_BY_ID = "SELECT * FROM criminals WHERE id = ?";

    //insert queries
    public static final String INSERT_CRIMINAL = "INSERT INTO criminals (name, surname, date_of_birth, citizenship, description, reward) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String INSERT_NEW_USER_WITH_LOGIN = "INSERT INTO users (login, password) VALUES (?, ?)";

    //update queries
    public static final String UPDATE_USER ="UPDATE users SET  login = ?, password = ? WHERE id = ?";
    public static final String UPDATE_CRIMINAL = "UPDATE criminals SET name = ?, " +
            "surname = ?, date_of_birth = ?, citizenship = ?, description = ?, " +
            "reward = ?, is_arrested = ? WHERE id = ?";

    //delete queries
    public static final String DELETE_CRIMINAL = "DELETE FROM criminals WHERE id = ?";
    public static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
}
