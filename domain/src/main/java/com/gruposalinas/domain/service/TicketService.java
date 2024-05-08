package com.gruposalinas.domain.service;

import com.gruposalinas.domain.repository.TicketRepository;
import com.gruposalinas.domain.model.Ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

  @Autowired
  private TicketRepository ticketRepository;

  // Obtener todos los tickets
  public List<Ticket> getAllTickets() {
    return ticketRepository.findAll();
  }

  // Obtener un ticket por ID
  public Ticket getTicketById(String id) {
    return ticketRepository.findById(id).orElse(null);
  }

  // Crear un nuevo ticket
  public Ticket createTicket(Ticket ticket) {
    return ticketRepository.save(ticket);
  }

  // Actualizar un ticket existente
  public Ticket updateTicket(String id, Ticket updatedTicket) {
    return ticketRepository.findById(id)
        .map(existingTicket -> {
          existingTicket.setTitle(updatedTicket.getTitle());
          existingTicket.setDescription(updatedTicket.getDescription());
          existingTicket.setEmail(updatedTicket.getEmail());
          existingTicket.setName(updatedTicket.getName());
          existingTicket.setCreationDate(updatedTicket.getCreationDate());
          existingTicket.setStatus(updatedTicket.getStatus());
          return ticketRepository.save(existingTicket);
        }).orElseGet(() -> ticketRepository.save(updatedTicket));
  }

  // Eliminar un ticket
  public void deleteTicket(String id) {
    ticketRepository.deleteById(id);
  }
}