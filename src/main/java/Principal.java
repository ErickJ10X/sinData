import java.net.URI;
import java.net.URISyntaxException;

public class Principal {
    public static void main(String[] args) throws URISyntaxException {
        ClienteHttp cliente = new ClienteHttp(new URI("https://jsonplaceholder.typicode.com/posts/1"));
        String respuesta = cliente.getRespuesta();
        System.out.println(respuesta);
    }
}
