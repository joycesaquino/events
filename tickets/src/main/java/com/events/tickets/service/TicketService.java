package com.events.tickets.service;

import com.events.customer.entity.Customer;
import com.events.tickets.dto.TicketCreateDTO;
import com.events.tickets.dto.TicketDTO;
import com.events.tickets.dto.TicketUpdateDTO;
import com.events.tickets.entity.Ticket;
import com.events.tickets.mapper.TicketMapper;
import com.events.tickets.repository.TicketRepository;
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
     * @param ticketCreateDTO the ticket data to create
     * @return the created ticket as DTO
     */
    @Transactional
    public TicketDTO createTicket(TicketCreateDTO ticketCreateDTO) {
        Ticket ticket = ticketMapper.toEntity(ticketCreateDTO);

        if (ticketCreateDTO.getCustomerId() != null) {
            Customer customer = new Customer();
            customer.setId(ticketCreateDTO.getCustomerId());
            ticket.setCustomer(customer);
        }

        Ticket savedTicket = ticketRepository.save(ticket);
        return ticketMapper.toDTO(savedTicket);
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
                    if (ticketUpdateDTO.getCustomerId() != null) {
                        Customer customer = new Customer();
                        customer.setId(ticketUpdateDTO.getCustomerId());
                        existingTicket.setCustomer(customer);
                    }

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
