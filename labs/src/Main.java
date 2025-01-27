import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        String userInput;
        System.out.print("Please enter a country name: ");
        userInput = scanner.nextLine();
        if (findCountry(userInput) != 0) {
            displayCharacterName();
        }
    }

    private static int findCountry(String countryName) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(String.format("https://restcountries.com/v3.1/name/" + countryName))).GET().build();
        HttpResponse<String> httpResponse;
        try (HttpClient client = HttpClient.newHttpClient()) {
            httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        }

        if (httpResponse.statusCode() == 200) {
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(httpResponse.body(), JsonArray.class);
            JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();

            Country country = new Country(jsonObject.getAsJsonObject("name").get("common").getAsString(),
                    jsonObject.get("population").getAsString(),
                    jsonObject.get("continents").isJsonObject() ? jsonObject.get("continents").getAsString() : jsonObject.getAsJsonArray("continents").get(0).getAsString(),
                    jsonObject.get("capital").getAsString());

            System.out.printf("%s is a great country with a great population of %s and is in %s continent. With the following capitals: %s.\n",
                    country.getName(), country.getPopulation(), country.getContinent(), country.getCapital());
            return 0;
        } else {
            String message = "Error! Country not found! Calling Star Wars API";
            System.out.printf("%s\n%s\n", message, "-".repeat(message.length()));
            return -1;
        }
    }

    private static void displayCharacterName() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI("https://swapi.dev/api/people/4/")).GET().build();

        HttpResponse<String> httpResponse;
        try (HttpClient client = HttpClient.newHttpClient()) {
            httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        }

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(httpResponse.body(), JsonObject.class);
        System.out.println(jsonObject.get("name").getAsString());
    }
}
