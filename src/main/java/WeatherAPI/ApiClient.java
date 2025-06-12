package WeatherAPI;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ApiClient {
    private final String APIKEY;
    OkHttpClient client = new OkHttpClient();

    public ApiClient(String apiKey) {
        this.APIKEY = apiKey;
    }

    public  String call(String country) {
        Request request = new Request.Builder()
                .url("http://api.weatherapi.com/v1/current.json?key="+ APIKEY +"&q="+ country +"&aqi=yes")
                .addHeader("Authorization", "Bearer " + APIKEY)
                .addHeader("accept", "application/json")
                .build();
        try (okhttp3.Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                System.out.println("Request failed: " + response.code());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
