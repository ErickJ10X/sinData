package Base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URISyntaxException;

public class Principal {
    public static void main(String[] args) throws URISyntaxException, JsonProcessingException {
        ClienteHttp clientePost = new ClienteHttp(new URI("https://jsonplaceholder.typicode.com/posts/1"), "GET");
        String respuestaPost = clientePost.getRespuesta();
        String usuarioId = respuestaPost.substring(respuestaPost.indexOf(":") + 2, respuestaPost.indexOf(","));
        ClienteHttp clienteUsuario = new ClienteHttp(new URI("https://jsonplaceholder.typicode.com/users/" + usuarioId), "GET");
        String respuestaUsuario = clienteUsuario.getRespuesta();
        respuestaUsuario = usuarioSinExtraDatos(respuestaUsuario);
        respuestaPost = respuestaPost.substring(0, respuestaPost.indexOf(":") + 1) + respuestaUsuario + respuestaPost.substring(respuestaPost.indexOf(","), respuestaPost.length());
        ObjectMapper mapper = new ObjectMapper();
        Post post = mapper.readValue(respuestaPost, Post.class);
        System.out.println(post);
    }

    public static String usuarioSinExtraDatos(String usuario) {
        int idStart = usuario.indexOf("\"id\":") + 5;
        int idEnd = usuario.indexOf(",", idStart);
        String id = usuario.substring(idStart, idEnd).trim();

        int nameKey = usuario.indexOf("\"name\":") + 8;
        int nameStart = usuario.indexOf("\"", nameKey) + 1;
        int nameEnd = usuario.indexOf("\"", nameStart);
        String name = usuario.substring(nameStart, nameEnd);

        int emailKey = usuario.indexOf("\"email\":") + 9;
        int emailStart = usuario.indexOf("\"", emailKey) + 1;
        int emailEnd = usuario.indexOf("\"", emailStart);
        String email = usuario.substring(emailStart, emailEnd);

        String resultado = "{ \"id\": " + id + ", \"name\": \"" + name + "\", \"email\": \"" + email + "\" }";
        return resultado;
    }
}
