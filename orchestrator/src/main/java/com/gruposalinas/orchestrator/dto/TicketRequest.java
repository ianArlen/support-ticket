package com.gruposalinas.orchestrator.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

public class TicketRequest {
  @NotBlank(message = "Title must not be blank")
  private String title;

  @NotBlank(message = "Description must not be blank")
  private String description;

  @NotBlank(message = "Area must not be blank")
  private String area;

  @Email(message = "Email should be valid")
  private String email;

  @NotBlank(message = "Name must not be blank")
  private String name;

  @NotNull(message = "Creation date must not be null")
  @PastOrPresent(message = "Creation date must be in the past or present")
  private String creationDate; // Cambiado a tipo String

  @NotBlank(message = "Status must not be blank")
  private String status;

  // Getters y Setters

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(String creationDate) {
    this.creationDate = creationDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
