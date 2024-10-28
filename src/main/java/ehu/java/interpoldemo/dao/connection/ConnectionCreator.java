package ehu.java.interpoldemo.dao.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static ehu.java.interpoldemo.dao.DaoConstants.*;

public class ConnectionCreator {

    public static Connection createConnection() {
        try {
            return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}
