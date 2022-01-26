import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection connection = null;

    public Connection Connect() {
        try {
            connection = DriverManager.getConnection(Utility.jdbcURL, Utility.dbUser, Utility.dbPassword);
            System.out.println("Connection to database successful");
        } catch (SQLException e) {
            System.out.println("Error - connection to database failed!");
            e.printStackTrace();
        }
    return connection;
    }
}
