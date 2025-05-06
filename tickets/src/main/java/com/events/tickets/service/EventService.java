package com.events.tickets.service;

import com.events.commons.entity.Event;
import com.events.commons.entity.Ticket;
import com.events.commons.enums.EventStatus;
import com.events.tickets.integrations.cep.ViaCepResponse;
import com.events.tickets.integrations.cep.ViaCepService;
import com.events.tickets.dto.EventCreateDTO;
import com.events.tickets.dto.EventDTO;
import com.events.tickets.dto.EventUpdateDTO;
import com.events.tickets.mapper.EventMapper;
import com.events.tickets.repository.EventRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing event operations.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final ViaCepService viaCepService;
    private final TicketService ticketService;


    public void updateEventAvailability(Long eventId) {
        boolean hasTicketAvailability = ticketService.validateTicketAvailability(eventId);
        log.info("Check if event {} is available {}.", eventId, hasTicketAvailability);
        if (!hasTicketAvailability) {
            return;
        }
        Event event = eventRepository.getEventById(eventId);
        event.setStatus(EventStatus.SOLD_OUT);
        eventRepository.save(event);
    }

    /**
     * Get all events.
     *
     * @return list of all events as DTOs
     */
    @Transactional(readOnly = true)
    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return eventMapper.toDTOList(events);
    }

    /**
     * Get an event by ID.
     *
     * @param id the event ID
     * @return an Optional containing the event DTO if found
     */
    @Transactional(readOnly = true)
    public Optional<EventDTO> getEventById(Long id) {
        return eventRepository.findById(id)
            .map(eventMapper::toDTO);
    }

    /**
     * Create a new event.
     *
     * @param dto the event data to create
     * @return the created event as DTO
     */
    @Transactional
    public EventDTO create(EventCreateDTO dto) {
        ViaCepResponse address = viaCepService.getAddressByZipCode(dto.getZipCode());
        Event event = eventRepository.save(eventMapper.toEntity(dto, address));
        return eventMapper.toDTO(event);
    }

    /**
     * Update an existing event.
     *
     * @param id the event ID
     * @param eventUpdateDTO the event data to update
     * @return the updated event as DTO
     */
    @Transactional
    public Optional<EventDTO> updateEvent(Long id, EventUpdateDTO eventUpdateDTO) {
        return eventRepository.findById(id)
            .map(existingEvent -> {
                Event updatedEvent = eventMapper.updateEventFromDTO(eventUpdateDTO, existingEvent);
                return eventMapper.toDTO(eventRepository.save(updatedEvent));
            });
    }

    /**
     * Delete an event by ID.
     *
     * @param id the event ID to delete
     */
    @Transactional
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}