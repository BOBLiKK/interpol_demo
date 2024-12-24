package ehu.java.interpoldemo.constants;

public final class DaoConstant {

    private DaoConstant() {
    }

    //select queries
    public static final String SELECT_ALL_CRIMINALS = "SELECT * FROM criminals";
    public static final String SELECT_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    public static final String SELECT_USER_ID_BY_LOGIN = "SELECT id FROM users WHERE login = ?";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    public static final String SELECT_ROLE_BY_LOGIN = "SELECT role FROM users WHERE login = ?";
    public static final String SELECT_CRIMINAL_BY_ID = "SELECT * FROM criminals WHERE id = ?";
    public static final String SELECT_REQUEST_BY_ID = "SELECT * FROM requests WHERE request_id = ?";
    public static final String SELECT_REQUEST_BY_USER_ID = "SELECT * FROM requests WHERE user_id = ?";
    public static final String SELECT_ALL_REQUESTS = "SELECT * FROM requests";


    //insert queries
    public static final String INSERT_CRIMINAL = "INSERT INTO criminals (name, surname, date_of_birth, citizenship, description, reward) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String INSERT_NEW_USER_WITH_LOGIN = "INSERT INTO users (login, password) VALUES (?, ?)";
    public static final String INSERT_REQUEST = "INSERT INTO requests (user_id, name, surname, date_of_birth, citizenship, description, reward, comment, status) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    //update queries
    public static final String UPDATE_USER ="UPDATE users SET  login = ?, password = ? WHERE id = ?";
    public static final String UPDATE_CRIMINAL = "UPDATE criminals SET name = ?, " +
            "surname = ?, date_of_birth = ?, citizenship = ?, description = ?, " +
            "reward = ?, is_arrested = ? WHERE id = ?";
    public static final String UPDATE_REQUEST_STATUS = "UPDATE requests SET status = ? WHERE request_id = ?";

    //delete queries
    public static final String DELETE_CRIMINAL = "DELETE FROM criminals WHERE id = ?";
    public static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
}
