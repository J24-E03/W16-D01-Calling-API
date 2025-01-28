package org.dcistudent.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractDTO {
  private Boolean present = false;

  public Boolean isPresent() {
    return this.present;
  }

  @JsonIgnore
  public void markAsPresent(Boolean present) {
    this.present = true;
  }

  @JsonIgnore
  public void markAsUnpresent() {
    this.present = false;
  }
}
