package org.dcistudent;

import org.dcistudent.dtos.countries.Country;
import org.dcistudent.factories.countries.CountryFactory;

import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    new Main();
  }

  public Main() {
    Main.exercise1();
  }

  private static void exercise1() {
    Country country = CountryFactory.getInstance().getByName("Cuba");
    if (country.isPresent() == false) {
      System.out.println("Country not found");
      return;
    }

    System.out.printf(
        "%s is a great country with a great population of %d and is in %s continent. With the following capitals: %s%n",
        country.getName().getCommon(),
        country.getPopulation(),
        Arrays.toString(country.getContinents()),
        country.getCapitals()
    );
  }
}