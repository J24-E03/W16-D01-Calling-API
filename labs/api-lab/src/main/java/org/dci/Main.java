package org.dci;

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
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a countries name you want to learn more about:\n");
        String inputCountry = scanner.nextLine();
        printCountry(inputCountry);
        scanner.close();
//        printCountry("GERMANY");
    }

    public static void printCountry(String country) throws URISyntaxException, IOException, InterruptedException {
//        building a request
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://restcountries.com/v3.1/name/" + country))
                .GET()
                .build();

//        setting up a client
        HttpClient httpClient = HttpClient.newHttpClient();

//        send a request
        HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

//        print response
        if(response.statusCode() != 200) {
            System.out.println("Sorry we couldn't find the country you are looking for :(");
            return;
        }
//        convert to Json
        Gson gson = new Gson();

        JsonArray convertedResponse = gson.fromJson(response.body(), JsonArray.class);

//    get country information
        String countryName = convertedResponse.get(0).getAsJsonObject().get("name")
                .getAsJsonObject().get("official").getAsString();

        int population = convertedResponse.get(0).getAsJsonObject().get("population").getAsInt();
        String continent = convertedResponse.get(0).getAsJsonObject().get("continents").getAsString();
        String capital = convertedResponse.get(0).getAsJsonObject().get("capital").getAsString();

        System.out.println(countryName + " is a great country with a great " +
                           "population of " + population + " and is in " +
                           continent + ". With the following capital: " + capital + ".\n");

    }
}