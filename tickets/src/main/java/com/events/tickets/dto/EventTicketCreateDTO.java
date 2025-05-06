package com.events.tickets.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventTicketCreateDTO {
    @NotNull(message = "Number of tickets cannot be null")
    private Long quantity;

    @NotNull(message = "TicketPrice cannot be null")
    private BigDecimal price;
}
