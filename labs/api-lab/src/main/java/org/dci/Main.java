package org.dci;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        System.out.println("Hello, World!");
        printCountry("Italy");
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
        System.out.println(response.body());

    }
}