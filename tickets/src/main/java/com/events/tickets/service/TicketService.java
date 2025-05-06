package com.events.tickets.service;

import com.events.commons.entity.Customer;
import com.events.commons.entity.Event;
import com.events.commons.entity.Ticket;
import com.events.tickets.dto.TicketDTO;
import com.events.tickets.dto.TicketUpdateDTO;
import com.events.commons.enums.TicketStatus;
import com.events.tickets.mapper.TicketMapper;
import com.events.tickets.repository.TicketRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing ticket operations.
 */
@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    /**
     * Get all tickets.
     *
     * @return list of all tickets as DTOs
     */
    @Transactional(readOnly = true)
    public List<TicketDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return ticketMapper.toDTOList(tickets);
    }

    /**
     * Get a ticket by ID.
     *
     * @param id the ticket ID
     * @return an Optional containing the ticket DTO if found
     */
    @Transactional(readOnly = true)
    public Optional<TicketDTO> getTicketById(Long id) {
        return ticketRepository.findById(id)
            .map(ticketMapper::toDTO);
    }

    /**
     * Create a new ticket.
     *
     * @param dto the ticket data to create
     * @return the created ticket as DTO
     */
    @Transactional
    public TicketDTO buyTicket(TicketUpdateDTO dto) {
        Ticket ticket = ticketMapper.toEntity(dto);
        Customer customer = new Customer();
        customer.setId(dto.getCustomerId());
        ticket.setCustomer(customer);

        ticket.setStatus(TicketStatus.RESERVED);

        return ticketMapper.toDTO(ticketRepository.save(ticket));
    }

    /**
     * Create a new ticket with a default status.
     *
     * @return the created ticket
     */
    public Ticket create(Event event, BigDecimal price) {
        Ticket ticket = new Ticket();
        ticket.setEvent(event);
        ticket.setStatus(TicketStatus.AVAILABLE);
        ticket.setPrice(price);
        return ticketRepository.save(ticket);
    }

    /**
     * Update an existing ticket.
     *
     * @param id the ticket ID
     * @param ticketUpdateDTO the ticket data to update
     * @return the updated ticket as DTO
     */
    @Transactional
    public Optional<TicketDTO> updateTicket(Long id, TicketUpdateDTO ticketUpdateDTO) {
        return ticketRepository.findById(id)
            .map(existingTicket -> {
                Customer customer = new Customer();
                customer.setId(ticketUpdateDTO.getCustomerId());
                existingTicket.setCustomer(customer);
                Ticket updatedTicket = ticketMapper.updateTicketFromDTO(ticketUpdateDTO, existingTicket);
                return ticketMapper.toDTO(ticketRepository.save(updatedTicket));
            });
    }

    /**
     * Delete a ticket by ID.
     *
     * @param id the ticket ID to delete
     */
    @Transactional
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}
