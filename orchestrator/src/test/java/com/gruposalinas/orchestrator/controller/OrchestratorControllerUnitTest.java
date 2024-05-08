package com.gruposalinas.orchestrator.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;

import com.gruposalinas.orchestrator.dto.TicketRequest;
import com.gruposalinas.orchestrator.dto.UserRequest;
import com.gruposalinas.orchestrator.exception.DataValidationException;

public class OrchestratorControllerUnitTest {

    private OrchestratorController controller;
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        restTemplate = mock(RestTemplate.class);
        controller = new OrchestratorController();
        controller.setRestTemplate(restTemplate);
    }

    @Test
    public void testCreateTicket_ValidRequest_ReturnsCreatedResponse() {
        TicketRequest validRequest = new TicketRequest();
        validRequest.setTitle("Sample Title");
        validRequest.setDescription("Sample Description");
        validRequest.setArea("Support");
        validRequest.setEmail("example@example.com");
        validRequest.setName("John Doe");
        validRequest.setCreationDate("2024-05-07");
        validRequest.setStatus("Open");

        ResponseEntity<String> responseEntity = new ResponseEntity<>("{'code':201, 'message': 'Recurso creado exitosamente'}", HttpStatus.CREATED);
        when(restTemplate.postForEntity("http://localhost:8080/tickets", validRequest, String.class))
                .thenReturn(responseEntity);

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        ResponseEntity<?> response = controller.createTicket(validRequest, bindingResult);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("{'code':201, 'message': 'Recurso creado exitosamente'}", response.getBody());
    }

    @Test
    public void testCreateTicket_InvalidRequest_ThrowsDataValidationException() {
        TicketRequest invalidRequest = new TicketRequest();
        invalidRequest.setTitle("");
        invalidRequest.setDescription("");
        invalidRequest.setArea("");
        invalidRequest.setEmail("");
        invalidRequest.setName("");
        invalidRequest.setCreationDate("");
        invalidRequest.setStatus("");

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        DataValidationException exception = assertThrows(DataValidationException.class, () -> {
            controller.createTicket(invalidRequest, bindingResult);
        });

        assertEquals("No se permiten campos vacíos en 'title', 'description', 'area', 'email', 'name', 'creationDate', 'status'.", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getStatusCode());
    }

    @Test
    public void testCreateTicket_InvalidEmail_ThrowsDataValidationException() {
        TicketRequest invalidRequest = new TicketRequest();
        invalidRequest.setTitle("Valid Title");
        invalidRequest.setDescription("Valid Description");
        invalidRequest.setArea("Support");
        invalidRequest.setEmail("invalid-email-format"); // Email intencionalmente inválido
        invalidRequest.setName("Valid Name");
        invalidRequest.setCreationDate("2024-05-07");
        invalidRequest.setStatus("Open");

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        DataValidationException exception = assertThrows(DataValidationException.class, () -> {
            controller.createTicket(invalidRequest, bindingResult);
        });

        assertEquals("El formato del email no es válido", exception.getMessage());
    }

    @Test
    public void testCreateTicket_InvalidName_ThrowsDataValidationException() {
        TicketRequest invalidRequest = new TicketRequest();
        invalidRequest.setTitle("Valid Title");
        invalidRequest.setDescription("Valid Description");
        invalidRequest.setArea("Support");
        invalidRequest.setEmail("example@example.com");
        invalidRequest.setName("InvalidName123"); // Nombre intencionalmente inválido con números
        invalidRequest.setCreationDate("2024-05-07");
        invalidRequest.setStatus("Open");

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        DataValidationException exception = assertThrows(DataValidationException.class, () -> {
            controller.createTicket(invalidRequest, bindingResult);
        });

        assertEquals("El formato del nombre no es válido", exception.getMessage());
    }



    
    @Test
    public void testCreateUser_ValidRequest_ReturnsCreatedResponse() {
        UserRequest validRequest = new UserRequest();
        validRequest.setName("Jane Doe");
        validRequest.setEmail("jane@example.com");
        validRequest.setCreationDate("2024-05-07");
    
        // Asegúrate de que la respuesta simulada coincida con lo que realmente devuelve el controlador.
        ResponseEntity<String> responseEntity = new ResponseEntity<>("{'code':201, 'message': 'Recurso creado exitosamente'}", HttpStatus.CREATED);
        when(restTemplate.postForEntity("http://localhost:8080/users", validRequest, String.class))
                .thenReturn(responseEntity);
    
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
    
        ResponseEntity<?> response = controller.createUser(validRequest, bindingResult);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        // Asegúrate de que el mensaje esperado coincida con el mensaje actual.
        assertEquals("{'code':201, 'message': 'Recurso creado exitosamente'}", response.getBody());
    }
    

    @Test
    public void testCreateUser_InvalidRequest_ThrowsDataValidationException() {
        UserRequest invalidRequest = new UserRequest();
        invalidRequest.setName("");
        invalidRequest.setEmail("");
        invalidRequest.setCreationDate("");

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        DataValidationException exception = assertThrows(DataValidationException.class, () -> {
            controller.createUser(invalidRequest, bindingResult);
        });

        assertEquals("No se permiten campos vacíos en 'name', 'email', 'creationDate'.", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getStatusCode());
    }
    @Test
    public void testCreateUser_InvalidName_ThrowsDataValidationException() {
        UserRequest invalidRequest = new UserRequest();
        invalidRequest.setName("1John Doe");
        invalidRequest.setEmail("JohnDoe@gmail.com");
        invalidRequest.setCreationDate("2024-05-04T00:00:00Z");
    
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
    
        DataValidationException exception = assertThrows(DataValidationException.class, () -> {
            controller.createUser(invalidRequest, bindingResult);
        });
    
        assertEquals("El formato del nombre no es válido", exception.getMessage());
    }  
    
    @Test
    public void testCreateUser_InvalidEmail_ThrowsDataValidationException() {
        UserRequest invalidRequest = new UserRequest();
        invalidRequest.setName("John Doe");
        invalidRequest.setEmail("JohnDoe@");
        invalidRequest.setCreationDate("2024-05-04T00:00:00Z");
    
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
    
        DataValidationException exception = assertThrows(DataValidationException.class, () -> {
            controller.createUser(invalidRequest, bindingResult);
        });
    
        assertEquals("El formato del email no es válido", exception.getMessage());
    }    

}
