package org.dcistudent.dtos.countries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryName {
  private String common;
  private String official;

  public String getCommon() {
    return this.common;
  }

  public void setCommon(String common) {
    this.common = common;
  }

  public String getOfficial() {
    return this.official;
  }

  public void setOfficial(String official) {
    this.official = official;
  }
}
