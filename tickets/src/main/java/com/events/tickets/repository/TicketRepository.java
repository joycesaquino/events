package com.events.tickets.repository;

import com.events.tickets.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing Ticket entities.
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    // Add custom query methods if needed
}