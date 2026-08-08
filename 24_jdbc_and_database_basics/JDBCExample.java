import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCExample {
    public static void main(String[] args) {
        // What: this sample shows the typical steps for JDBC database access.
        // Why: understanding these steps helps you use higher-level frameworks safely.
        // How: open a connection, prepare a statement, execute SQL, and close resources.
        System.out.println("JDBC workflow:");
        System.out.println("1. Load the database driver");
        System.out.println("2. Open a Connection");
        System.out.println("3. Create a Statement or PreparedStatement");
        System.out.println("4. Execute SQL and read ResultSet");
        System.out.println("5. Close resources");

        String sql = "SELECT id, name FROM users WHERE active = ?";
        System.out.println("Example SQL: " + sql);
    }
}
