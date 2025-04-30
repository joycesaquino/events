package com.events.tickets.dto;

import com.events.tickets.enums.EventStatus;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) for updating an existing Event.
 * Contains only the fields that can be updated.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventUpdateDTO {
    private EventStatus status;
    private List<Long> ticketIds;
}