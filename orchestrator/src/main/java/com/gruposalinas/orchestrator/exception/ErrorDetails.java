package com.gruposalinas.orchestrator.exception;

import java.util.Date;

public class ErrorDetails {
  private Date timestamp;
  private String message;
  private String details;
  private String errorCode;
  private String suggestion;

  // Constructor con todos los parámetros
  public ErrorDetails(Date timestamp, String message, String details, String errorCode, String suggestion) {
    this.timestamp = timestamp;
    this.message = message;
    this.details = details;
    this.errorCode = errorCode;
    this.suggestion = suggestion;
  }

  // Getters
  public Date getTimestamp() {
    return timestamp;
  }

  public String getMessage() {
    return message;
  }

  public String getDetails() {
    return details;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public String getSuggestion() {
    return suggestion;
  }

  // Setters
  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public void setSuggestion(String suggestion) {
    this.suggestion = suggestion;
  }
}
