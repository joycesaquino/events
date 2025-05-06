package com.events.tickets.facade;

import com.events.tickets.dto.EventCreateDTO;
import com.events.tickets.dto.EventDTO;
import com.events.tickets.service.EventService;
import com.events.tickets.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventFacade {

  private final EventService eventService;
  private final TicketService ticketService;

  public EventDTO createEvent(EventCreateDTO eventCreateDTO) {
    EventDTO event = eventService.create(eventCreateDTO);
    ticketService.create(event, eventCreateDTO.getTicket());
    return event;
  }

}
