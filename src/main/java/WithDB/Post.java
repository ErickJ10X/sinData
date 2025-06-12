package WithDB;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class Post implements Serializable {
    private int userId;
    private int id;
    private String title;
    private String body;
    private Timestamp date;

    @Override
    public String toString() {
        return """
                usuario: %d
                id: %d
                title: %s
                body: %s
                date: %s
                
                """.formatted(userId, id, title, body, date);
    }
}

