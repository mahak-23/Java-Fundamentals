import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCPreparedStatementExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String user = "user";
        String password = "password";

        String query = "SELECT id, name FROM users WHERE active = ?";

        // What: uses PreparedStatement to safely set SQL parameters.
        // Why: avoids SQL injection and improves reuse of SQL execution plans.
        // How: set values with index-based setters and execute the query.
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setBoolean(1, true);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    System.out.println("user=" + id + ", " + name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
