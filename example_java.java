import java.sql.Connection;
import java.sql.Statement;

public class TestSQL {
    public void getUserInfo(String userId, Connection conn) throws Exception {
        String query = "SELECT * FROM users WHERE id = " + userId; // Potential SQL Injection
        Statement stmt = conn.createStatement();
        stmt.execute(query);

        // Another risky SQL pattern
        String query2 = "DELETE FROM users WHERE name = '" + userId + "'";
        stmt.execute(query2);

        // Additional risky patterns
        String query3 = "INSERT INTO logs VALUES ('" + userId + "')";
        stmt.execute(query3);

        String query4 = "UPDATE users SET name = '" + userId + "' WHERE id = 1";
        stmt.execute(query4);
    }
}

