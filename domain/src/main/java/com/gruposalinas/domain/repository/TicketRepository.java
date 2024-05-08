package com.gruposalinas.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gruposalinas.domain.model.Ticket;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {
    
}
