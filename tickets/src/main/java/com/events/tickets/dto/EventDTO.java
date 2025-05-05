package com.events.tickets.dto;

import com.events.commons.enums.EventStatus;
import java.time.OffsetDateTime;
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
    private String zipCode;
    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
