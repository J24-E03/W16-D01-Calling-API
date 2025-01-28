package org.dcistudent.factories.countries;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dcistudent.dtos.countries.Country;
import org.dcistudent.resources.Apis;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CountryFactory {
  private final HttpClient httpClient = HttpClient.newHttpClient();
  private final HttpRequest.Builder builder = HttpRequest.newBuilder();

  private CountryFactory() {}

  public static CountryFactory getInstance() {
    return new CountryFactory();
  }

  private Country[] getResponse(HttpRequest request) throws IOException, InterruptedException {
    String response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
    ObjectMapper mapper = new ObjectMapper();

    return mapper.readValue(response, Country[].class);
  }

  public Country getByName(String name) {
    HttpRequest request = this.builder
        .uri(URI.create(Apis.REST_COUNTRIES + "/name/" + name))
        .GET()
        .build();

    try {
      Country[] response = this.getResponse(request);
      if (response.length == 0) {
        Country country = new Country();
        country.markAsUnpresent();
        return country;
      }

      return response[0];
    } catch (InterruptedException | IOException e) {
      System.out.println("Error: " + e.getMessage());
      throw new RuntimeException(e);
    }
  }
}
