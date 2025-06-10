package Base;

import java.sql.Connection;

public class PostgreSQL {
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return java.sql.DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/posts",
                    "root",
                    "123"
            );
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Base.PostgreSQL JDBC Driver not found", e);
        } catch (java.sql.SQLException e) {
            throw new RuntimeException("Connection to Base.PostgreSQL failed", e);
        }
    }
}
