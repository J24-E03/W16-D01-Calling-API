package org.dci;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            getCountryInformation(scanner);
            getActorsInfo(scanner);
        } catch (URISyntaxException | IOException | InterruptedException | IllegalArgumentException e) {
            System.out.printf(e.getMessage());
        }

        scanner.close();
    }

    private static void getCountryInformation(Scanner scanner) throws URISyntaxException, IOException, InterruptedException {
        System.out.println("Task1:");
        System.out.println("Please enter a country name: ");
        String userInput = scanner.nextLine();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(String.format("https://restcountries.com/v3.1/name/%s", userInput)))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 404) {
            throw new IllegalArgumentException("Country not found. Please check the spelling and try again later.");
        }

        Gson gson = new Gson();
        JsonArray countryInfo = gson.fromJson(response.body(), JsonArray.class);
        JsonObject country = countryInfo.get(0).getAsJsonObject();

        System.out.printf("%s is a great country with a great population of %d" +
                " and is in %s continent. With the following capitals: %s\n", country.get("name").getAsJsonObject().get("official").getAsString(),
                country.get("population").getAsInt(), country.get("continents").getAsString(), country.get("capital").getAsString());
    }

    private static void getActorsInfo(Scanner scanner) throws URISyntaxException, IOException, InterruptedException {
        System.out.println("\nTask2:");
        System.out.println("Please enter a number to display its related actor infos in The Star Sar movie: : ");
        String userInput = scanner.nextLine();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(String.format("https://swapi.dev/api/people/%s", userInput)))
            .GET()
            .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 404) {
            throw new IllegalArgumentException("Actor not found. Please check the spelling and try again later.");
        }

        Gson gson = new Gson();
        JsonObject actor = gson.fromJson(response.body(), JsonObject.class);

        System.out.println("Actor Information:");
        System.out.println("Name: " + actor.get("name").getAsString());
        System.out.println("Gender: " + actor.get("gender").getAsString());
        System.out.println("Birth year: " + actor.get("birth_year").getAsString());
        System.out.println("Home world: " + getHomeWorld(actor.get("homeworld").getAsString()));
        System.out.println("Films:");
        actor.getAsJsonArray("films").forEach(movie -> {
            try {
                displayMovieInfo(movie.getAsString());
            } catch (URISyntaxException | InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private static String getHomeWorld(String homeworld) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(homeworld))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 404) {
            throw new IllegalArgumentException("home world not found. Please check the spelling and try again later.");
        }

        Gson gson = new Gson();

        return gson.fromJson(response.body(), JsonObject.class).get("name").getAsString();

    }

    private static void displayMovieInfo(String movieUrl) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(movieUrl))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 404) {
            throw new IllegalArgumentException("Movie not found. Please check the spelling and try again later.");
        }

        Gson gson = new Gson();
        JsonObject movie = gson.fromJson(response.body(), JsonObject.class);
        System.out.printf("Title: %s, Release Date: %s\n",
                 movie.get("title").getAsString(), movie.get("release_date").getAsString());


    }
}