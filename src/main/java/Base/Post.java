package Base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class Post implements Serializable {
    @JsonProperty("userId")
    private Usuario usuario;
    private int id;
    private String title;
    private String body;

    @Override
    public String toString() {
        return """
                Post
                ----
                usuario: %s
                id: %d
                title: %s
                body: %s
                """.formatted(usuario, id, title, body);
    }
}
