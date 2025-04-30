package com.events.tickets.dto;

import com.events.tickets.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) for creating a new Ticket.
 * Contains only the fields needed for ticket creation.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketCreateDTO {
    private Long customerId;
    private TicketStatus status;
}