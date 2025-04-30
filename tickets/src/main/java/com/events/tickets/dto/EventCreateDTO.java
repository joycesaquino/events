package com.events.tickets.dto;

import com.events.tickets.enums.EventStatus;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) for creating a new Event.
 * Contains only the fields needed for event creation.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventCreateDTO {
    private EventStatus status;
    private List<Long> ticketIds;
}