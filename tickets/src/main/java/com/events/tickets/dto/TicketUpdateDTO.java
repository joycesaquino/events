package com.events.tickets.dto;

import com.events.commons.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

/**
 * DTO (Data Transfer Object) for updating an existing Ticket.
 * Contains only the fields that can be updated.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketUpdateDTO {

    @NotNull
    private Long customerId;
    private TicketStatus status;
}
