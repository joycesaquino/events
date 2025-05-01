package com.events.tickets.dto;

import com.events.commons.enums.TicketStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) for Ticket entity.
 * Used for transferring ticket data between layers.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    private Long id;

    @NotNull
    @NotEmpty
    private Long customerId;
    private TicketStatus status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

}
