package WithDB;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dao {
    private Connection connection;

    public Dao(Connection connection) {
        this.connection = connection;
    }

    public void insert(Object obj) {
        String sql = "INSERT INTO posteos (userid, id, title, body, Date) VALUES (?, ?, ?, ?, ?)";
        try (var stmt = connection.prepareStatement(sql)) {
            if (obj instanceof Post post) {
                stmt.setInt(1, post.getUserId());
                stmt.setInt(2, post.getId());
                stmt.setString(3, post.getTitle());
                stmt.setString(4, post.getBody());
                stmt.setTimestamp(5, post.getDate());
                stmt.executeUpdate();
            } else {
                throw new IllegalArgumentException("Unsupported object type: " + obj.getClass().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Post> findAll() {
        String sql = "SELECT * FROM posteos";
        try (var stmt = connection.createStatement();
             var rs = stmt.executeQuery(sql)) {
            List<Post> posts = new ArrayList<>();
            while (rs.next()) {
                Post post = new Post();
                post.setUserId(rs.getInt("userId"));
                post.setId(rs.getInt("id"));
                post.setTitle(rs.getString("title"));
                post.setBody(rs.getString("body"));
                post.setDate(rs.getTimestamp("Date"));
                posts.add(post);
            }
            return posts;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
