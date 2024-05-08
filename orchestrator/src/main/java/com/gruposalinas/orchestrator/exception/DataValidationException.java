package com.gruposalinas.orchestrator.exception;

public class DataValidationException extends RuntimeException {
  private String errorCode;
  private String suggestion;

  // Constructor con mensaje, código de error y sugerencia
  public DataValidationException(String message, String errorCode, String suggestion) {
    super(message);
    this.errorCode = errorCode;
    this.suggestion = suggestion;
  }

  // Método para obtener el código de estado HTTP
  public int getStatusCode() {
    // Devuelve un código de estado HTTP 400 (Bad Request) por defecto
    return 400;
  }

  // Getters y Setters
  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getSuggestion() {
    return suggestion;
  }

  public void setSuggestion(String suggestion) {
    this.suggestion = suggestion;
  }
}
