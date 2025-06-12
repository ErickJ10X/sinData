package WeatherAPI;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String country;
        String apiKey;
        System.out.print("Enter your API key: ");
        apiKey = scanner.nextLine().trim();
        System.out.print("Enter the name of the country: ");
        country = scanner.nextLine().trim();
        if (country.isEmpty()) {
            System.out.println("Country name cannot be empty.");
            return;
        }
        ApiClient apiClient = new ApiClient(apiKey);
        String response = apiClient.call(country);
        if (response != null) {
            System.out.println("Weather data for " + country + ": " + response);
        } else {
            System.out.println("Failed to retrieve weather data.");
        }
    }
}
