package com.events.tickets.dto;

import com.events.tickets.enums.EventStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private EventStatus status;

    @NotNull
    private Long tickets;
}