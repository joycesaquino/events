package com.events.tickets.mapper;

import com.events.tickets.dto.TicketDTO;
import com.events.tickets.dto.TicketUpdateDTO;
import com.events.commons.entity.Ticket;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between Ticket entity and DTOs.
 * Uses Java's Function interface for mapping operations.
 */
@Component
public class TicketMapper {

    /**
     * Function to convert Ticket entity to TicketDTO.
     */
    private final Function<Ticket, TicketDTO> ticketToDtoMapper = ticket -> {
        if (ticket == null) {
            return null;
        }

        TicketDTO dto = new TicketDTO();
        dto.setId(ticket.getId());
//        dto.setCustomerId(ticket.getCustomer() != null ? ticket.getCustomer().getId() : null);
        dto.setStatus(ticket.getStatus());
        dto.setCreatedAt(ticket.getCreatedAt());
        dto.setUpdatedAt(ticket.getUpdatedAt());
        return dto;
    };

    /**
     * Convert Ticket entity to TicketDTO.
     *
     * @param ticket the ticket entity
     * @return the ticket DTO
     */
    public TicketDTO toDTO(Ticket ticket) {
        return ticketToDtoMapper.apply(ticket);
    }

    /**
     * Convert a list of Ticket entities to a list of TicketDTOs.
     *
     * @param tickets the list of ticket entities
     * @return the list of ticket DTOs
     */
    public List<TicketDTO> toDTOList(List<Ticket> tickets) {
        if (tickets == null) {
            return null;
        }
        return tickets.stream()
            .map(ticketToDtoMapper)
            .collect(Collectors.toList());
    }

    /**
     * Convert TicketCreateDTO to Ticket entity.
     * Note: This method does not set the Customer entity, it should be set separately.
     *
     * @param ticketUpdateDTO the ticket create DTO
     * @return the ticket entity
     */
    public Ticket toEntity(TicketUpdateDTO ticketUpdateDTO) {
        if (ticketUpdateDTO == null) {
            return null;
        }

        Ticket ticket = new Ticket();
        ticket.setStatus(ticketUpdateDTO.getStatus());
        return ticket;
    }

    /**
     * Update Ticket entity from TicketUpdateDTO.
     * Note: This method does not update the Customer entity, it should be updated separately.
     *
     * @param ticketUpdateDTO the ticket update DTO
     * @param ticket the ticket entity to update
     * @return the updated ticket entity
     */
    public Ticket updateTicketFromDTO(TicketUpdateDTO ticketUpdateDTO, Ticket ticket) {
        if (ticketUpdateDTO == null || ticket == null) {
            return ticket;
        }

        ticket.setStatus(ticketUpdateDTO.getStatus());
        return ticket;
    }
}
