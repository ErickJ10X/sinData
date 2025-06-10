package Base;

import java.sql.Connection;

public class Dao {
    private Connection connection;

    public Dao(Connection connection) {
        this.connection = connection;
    }

    public void insert(Object obj) {
        String sql = "INSERT INTO posteos (userId, id, title, body) VALUES (?, ?, ?, ?)";
        try (var stmt = connection.prepareStatement(sql)) {
            if (obj instanceof Post post) {
                stmt.setInt(1, post.getUsuario().getId());
                stmt.setInt(2, post.getId());
                stmt.setString(3, post.getTitle());
                stmt.setString(4, post.getBody());
                stmt.executeUpdate();
            } else {
                throw new IllegalArgumentException("Unsupported object type: " + obj.getClass().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
