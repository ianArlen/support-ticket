package com.gruposalinas.orchestrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.gruposalinas.orchestrator.dto.TicketRequest;
import com.gruposalinas.orchestrator.dto.UserRequest;
import com.gruposalinas.orchestrator.exception.DataValidationException;
import com.gruposalinas.orchestrator.exception.ServiceUnavailableException;
import com.gruposalinas.orchestrator.util.Validator;

import org.springframework.http.HttpStatus;

import javax.validation.Valid;

@RestController
public class OrchestratorController {
  
  @Autowired
  private RestTemplate restTemplate;

  private boolean servicioExternoNoDisponible = false;

  public void setRestTemplate(RestTemplate restTemplate) {
      this.restTemplate = restTemplate;
  }

  private boolean isNullOrEmpty(String str) {
      return str == null || str.isEmpty();
  }

  @PostMapping("/tickets")
  public ResponseEntity<?> createTicket(@Valid @RequestBody TicketRequest ticketRequest, BindingResult bindingResult) {
      if (bindingResult.hasErrors()) {
          return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
      }
      
      if (servicioExternoNoDisponible) {
        throw new ServiceUnavailableException("El servicio externo no está disponible en este momento.");
      }
      
      if (ticketRequest == null || 
        isNullOrEmpty(ticketRequest.getTitle()) || 
        isNullOrEmpty(ticketRequest.getDescription()) || 
        isNullOrEmpty(ticketRequest.getArea()) || 
        isNullOrEmpty(ticketRequest.getEmail()) || 
        isNullOrEmpty(ticketRequest.getName()) || 
        isNullOrEmpty(ticketRequest.getCreationDate()) || 
        isNullOrEmpty(ticketRequest.getStatus())) {
        throw new DataValidationException("No se permiten campos vacíos en 'title', 'description', 'area', 'email', 'name', 'creationDate', 'status'.",
        "400", "Verifica que los datos enviados sean correctos.");
      }

      if(!Validator.isValidEmail(ticketRequest.getEmail())){
        throw new DataValidationException("El formato del email no es válido", "400", "Verifica que los datos enviados sean correctos.");
      }
  
      if(!Validator.isValidName(ticketRequest.getName())){
        throw new DataValidationException("El formato del nombre no es válido", "400", "Verifica que los datos enviados sean correctos.");
      }

      String ticketsUrl = "http://localhost:8080/tickets";
      HttpEntity<TicketRequest> request = new HttpEntity<>(ticketRequest);
      ResponseEntity<?> response = restTemplate.postForEntity(ticketsUrl, request, String.class);
      
      return ResponseEntity.status(HttpStatus.CREATED).body("{'code':201, 'message': 'Recurso creado exitosamente'}");
  }
  
  @PostMapping("/users")
  public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
        return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
    }
    
    if (servicioExternoNoDisponible) {
      throw new ServiceUnavailableException("El servicio externo no está disponible en este momento.");
    }

    if (userRequest == null || 
        isNullOrEmpty(userRequest.getName()) || 
        isNullOrEmpty(userRequest.getEmail()) || 
        isNullOrEmpty(userRequest.getCreationDate())) {
      throw new DataValidationException("No se permiten campos vacíos en 'name', 'email', 'creationDate'.", "400", "Verifica que los datos enviados sean correctos.");
    }

    if(!Validator.isValidEmail(userRequest.getEmail())){
      throw new DataValidationException("El formato del email no es válido", "400", "Verifica el email proporcionado.");
    }

    if(!Validator.isValidName(userRequest.getName())){
      throw new DataValidationException("El formato del nombre no es válido", "400", "Verifica el nombre proporcionado.");
    }

    String usersUrl = "http://localhost:8080/users";
    HttpEntity<UserRequest> request = new HttpEntity<>(userRequest);
    ResponseEntity<?> response = restTemplate.postForEntity(usersUrl, request, String.class);
    
    return ResponseEntity.status(HttpStatus.CREATED).body("{'code':201, 'message': 'Recurso creado exitosamente'}");
  }
}
