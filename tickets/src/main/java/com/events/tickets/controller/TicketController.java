package com.events.tickets.controller;

import com.events.tickets.dto.ReserveTicketDTO;
import com.events.tickets.dto.TicketDTO;
import com.events.tickets.dto.TicketUpdateDTO;
import com.events.tickets.facade.TicketFacade;
import com.events.tickets.mapper.TicketMapper;
import com.events.tickets.service.TicketService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for ticket operations.
 */
@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final TicketFacade ticketFacade;

    /**
     * Buy a ticket FACADE.
     *
     * @param reserveTicketDTO the ticket data with customer information
     * @return the purchased ticket DTO
     */
    @PostMapping("/buy")
    public ResponseEntity<List<TicketDTO>> buyTicket(@RequestBody ReserveTicketDTO reserveTicketDTO)
    {
        ticketFacade.createTicket(reserveTicketDTO);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get all tickets.
     *
     * @return list of all tickets as DTOs
     */
    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    /**
     * Get a ticket by ID.
     *
     * @param id the ticket ID
     * @return the ticket DTO if found, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Update an existing ticket.
     *
     * @param id the ticket ID
     * @param ticketUpdateDTO the ticket data to update
     * @return the updated ticket DTO if found, or 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable Long id, @RequestBody TicketUpdateDTO ticketUpdateDTO) {
        return ticketService.updateTicket(id, ticketUpdateDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete a ticket by ID.
     *
     * @param id the ticket ID
     * @return 204 No Content if successful, 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        if (ticketService.getTicketById(id).isPresent()) {
            ticketService.deleteTicket(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}