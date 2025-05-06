package com.events.tickets.facade;

import com.events.tickets.dto.ReserveTicketDTO;
import com.events.tickets.dto.TicketDTO;
import com.events.tickets.integrations.payment.PaymentService;
import com.events.tickets.service.EventService;
import com.events.tickets.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketFacade {

    private final EventService eventService;
    private final TicketService ticketService;
    private final PaymentService paymentService;

    @Transactional
    public void createTicket(ReserveTicketDTO reserveTicketDTO) {
        paymentService.processPayment(reserveTicketDTO);
        ticketService.reserveTickets(reserveTicketDTO);
        eventService.updateEventAvailability(reserveTicketDTO.getEventId());
    }


}
