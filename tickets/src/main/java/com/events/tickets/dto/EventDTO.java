package com.events.tickets.dto;

import com.events.tickets.enums.EventStatus;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) for Event entity.
 * Used for transferring event data between layers.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    private Long id;
    private EventStatus status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private List<Long> ticketIds;
}