package com.gruposalinas.domain.crontroller;

import com.gruposalinas.domain.repository.TicketRepository;
import com.gruposalinas.domain.model.Ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {

  @Autowired
  private TicketRepository ticketRepository;

  // Obtener todos los tickets
  @GetMapping
  public List<Ticket> getAllTickets() {
    return ticketRepository.findAll();
  }

  // Obtener un ticket por ID
  @GetMapping("/{id}")
  public ResponseEntity<Ticket> getTicketById(@PathVariable String id) {
    Optional<Ticket> ticket = ticketRepository.findById(id);
    return ticket.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  // Crear un nuevo ticket
  @PostMapping
  public Ticket createTicket(@RequestBody Ticket ticket) {
    return ticketRepository.save(ticket);
  }

  // Actualizar un ticket existente
  @PutMapping("/{id}")
  public ResponseEntity<Ticket> updateTicket(@PathVariable String id, @RequestBody Ticket updatedTicket) {
    return ticketRepository.findById(id)
        .map(existingTicket -> {
          existingTicket.setTitle(updatedTicket.getTitle());
          existingTicket.setDescription(updatedTicket.getDescription());
          existingTicket.setEmail(updatedTicket.getEmail());
          existingTicket.setName(updatedTicket.getName());
          existingTicket.setCreationDate(updatedTicket.getCreationDate());
          existingTicket.setStatus(updatedTicket.getStatus());
          Ticket savedTicket = ticketRepository.save(existingTicket);
          return ResponseEntity.ok(savedTicket);
        }).orElseGet(() -> ResponseEntity.notFound().build());
  }

  // Eliminar un ticket
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTicket(@PathVariable String id) {
    if (ticketRepository.existsById(id)) {
      ticketRepository.deleteById(id);
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
