package WithOkHttp;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.Arrays;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://jsonplaceholder.typicode.com/posts/?userId=1")
                .addHeader("user-agent", "okhttp/4.9.0")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println(response.code());
                ObjectMapper mapper = new ObjectMapper();
                List<Post> posts = Arrays.asList(mapper.readValue(response.body().string(), Post[].class));
                posts.forEach(System.out::println);
            } else {
                System.out.println("Request failed: " + response.code());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
