package com.events.tickets.repository;

import com.events.commons.entity.Ticket;
import com.events.commons.enums.TicketStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing Ticket entities.
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

  List<Ticket> findTicketsByEventIdAndStatus(Long eventId, TicketStatus status, Limit limit);
}
