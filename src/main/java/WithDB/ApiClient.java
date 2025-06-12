package WithDB;

import okhttp3.OkHttpClient;

public class ApiClient {
    private static final OkHttpClient client = new OkHttpClient();
    private static final String API_URL = "https://jsonplaceholder.typicode.com/posts";

    public String getPosts() {
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(API_URL)
                .build();
        try (okhttp3.Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to fetch posts: " + response.code());
            }
            return response.body().string();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching posts", e);
        }
    }

    public String getPostsByUserId(int userId) {
        String url = API_URL + "?userId=" + userId;
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .build();
        try (okhttp3.Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to fetch posts for user " + userId + ": " + response.code());
            }
            return response.body().string();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching posts for user " + userId, e);
        }
    }
}