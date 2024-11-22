package ehu.java.interpoldemo.constants;

public final class DaoConstant {

    private DaoConstant() {
    }


    //select queries
    public static final String SELECT_ALL_CRIMINALS = "SELECT * FROM criminals";
    public static final String SELECT_USER_BY_LOGIN = "SELECT password FROM users WHERE login = ?";

    public static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";



    //insert queries
    public static final String INSERT_CRIMINAL = "INSERT INTO criminals (name, surname, dateOfBirth, citizenship, description, reward, isArrested) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_NEW_USER = "INSERT INTO users (login, password) VALUES (?, ?)";
}
