package com.events.tickets.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveTicketDTO {
  Long customerId;
  Long eventId;
  Integer ticketsQuantity;
}
