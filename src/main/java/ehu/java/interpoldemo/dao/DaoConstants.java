package ehu.java.interpoldemo.dao;

public class DaoConstants {
    public static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/Interpol";
    public static final String DATABASE_USER = "postgres";
    public static final String DATABASE_PASSWORD = "Kukaracha336";
    public static final String INSERT_CRIMINAL = "INSERT INTO criminals (name, surname, dateOfBirth, citizenship, description, reward, isArrested) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String SELECT_ALL_CRIMINALS = "SELECT * FROM criminals";
    public static final String SELECT_USER_BY_LOGIN = "SELECT password FROM users WHERE login = ?";
    public static final String INSERT_NEW_USER = "INSERT INTO users (login, password) VALUES (?, ?)";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
}
