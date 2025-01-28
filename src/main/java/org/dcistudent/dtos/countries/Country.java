package org.dcistudent.dtos.countries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.dcistudent.dtos.AbstractDTO;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country extends AbstractDTO {
  private CountryName name = new CountryName();
  private String[] capital;
  private String region;
  private String subregion;
  private Integer population;
  private String[] borders;
  private String[] timezones;
  private String[] continents;
  private String[] callingCodes;
  private String[] topLevelDomain;
  private String flag;

  public Country() {
    super.markAsPresent(true);
  }

  public CountryName getName() {
    return this.name;
  }

  public String[] getCapitals() {
    return this.capital;
  }

  public void setCapitals(String[] capitals) {
    this.capital = capitals;
  }

  public String getRegion() {
    return this.region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getSubregion() {
    return this.subregion;
  }

  public void setSubregion(String subregion) {
    this.subregion = subregion;
  }

  public Integer getPopulation() {
    return this.population;
  }

  public void setPopulation(Integer population) {
    this.population = population;
  }

  public String[] getBorders() {
    return this.borders;
  }

  public void setBorders(String[] borders) {
    this.borders = borders;
  }

  public String[] getTimezones() {
    return this.timezones;
  }

  public void setTimezones(String[] timezones) {
    this.timezones = timezones;
  }

  public String[] getContinents() {
    return this.continents;
  }

  public void setContinents(String[] continents) {
    this.continents = continents;
  }

  public String[] getCallingCodes() {
    return this.callingCodes;
  }

  public void setCallingCodes(String[] callingCodes) {
    this.callingCodes = callingCodes;
  }

  public String[] getTopLevelDomain() {
    return this.topLevelDomain;
  }

  public void setTopLevelDomain(String[] topLevelDomain) {
    this.topLevelDomain = topLevelDomain;
  }

  public String getFlag() {
    return this.flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }

  @Override
  public String toString() {
    return "Country{" +
      "countryName{" +
      "official='" + this.getName().getOfficial() + '\'' +
      ", common='" + this.getName().getCommon() + '\'' +
      '}' +
      ", capitals='" + Arrays.toString(this.getCapitals()) + '\'' +
      ", region='" + this.getRegion() + '\'' +
      ", subregion='" + this.getSubregion() + '\'' +
      ", population=" + this.getPopulation() +
      ", borders=" + java.util.Arrays.toString(this.getBorders()) +
      ", timezones=" + java.util.Arrays.toString(this.getTimezones()) +
      ", continents=" + java.util.Arrays.toString(this.getContinents()) +
      ", callingCodes=" + java.util.Arrays.toString(this.getCallingCodes()) +
      ", topLevelDomain=" + java.util.Arrays.toString(this.getTopLevelDomain()) +
      ", flag='" + this.getFlag() + '\'' +
      '}';
  }
}
