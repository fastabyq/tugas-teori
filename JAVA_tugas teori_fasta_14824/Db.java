import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    public Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/";
            String user = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
