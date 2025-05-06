package com.events.tickets.service;

import com.events.commons.entity.Event;
import com.events.tickets.cep.ViaCepResponse;
import com.events.tickets.cep.ViaCepService;
import com.events.tickets.dto.EventCreateDTO;
import com.events.tickets.dto.EventDTO;
import com.events.tickets.dto.EventUpdateDTO;
import com.events.tickets.mapper.EventMapper;
import com.events.tickets.repository.EventRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing event operations.
 */
@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final TicketService ticketService;
    private final ViaCepService viaCepService;

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
    public EventDTO createEvent(EventCreateDTO dto) {
        ViaCepResponse address = viaCepService.getAddressByZipCode(dto.getZipCode());
        Event event = eventRepository.save(eventMapper.toEntity(dto, address));
        for (int i = 0; i < dto.getTickets(); i++) {
            ticketService.create(event, dto.getTickerPrice());
        }
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