package com.events.tickets.dto;

import com.events.tickets.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) for updating an existing Ticket.
 * Contains only the fields that can be updated.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketUpdateDTO {
    private Long customerId;
    private TicketStatus status;
}