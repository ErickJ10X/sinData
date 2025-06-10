package Base;

import lombok.Data;

import java.io.Serializable;

@Data
public class Usuario implements Serializable {
    private int id;
    private String name;
    private String email;

    @Override
    public String toString() {
        return """
                Usuario - id: %d nombre: %s email: %s""".formatted(id, name, email);
    }
}
