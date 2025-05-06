package com.events.tickets.dto;

import com.events.commons.enums.EventStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;
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

    @NotNull(message = "Status cannot be null")
    private EventStatus status;

    @NotNull
    EventTicketCreateDTO ticket;

    @NotNull(message = "ZipCode cannot be null")
    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "Invalid CEP format")
    private String zipCode;

}
