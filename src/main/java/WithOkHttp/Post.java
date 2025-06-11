package WithOkHttp;

import lombok.Data;

@Data
public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;

    @Override
    public String toString() {
        return """
                usuario: %d
                id: %d
                title: %s
                body: %s
                """.formatted(userId, id, title, body);
    }
}
