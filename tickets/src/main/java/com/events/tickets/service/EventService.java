package com.events.tickets.service;

import com.events.tickets.dto.EventCreateDTO;
import com.events.tickets.dto.EventDTO;
import com.events.tickets.dto.EventUpdateDTO;
import com.events.tickets.entity.Event;
import com.events.tickets.entity.Ticket;
import com.events.tickets.mapper.EventMapper;
import com.events.tickets.repository.EventRepository;
import com.events.tickets.repository.TicketRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
    private final TicketRepository ticketRepository;
    private final EventMapper eventMapper;

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
     * @param eventCreateDTO the event data to create
     * @return the created event as DTO
     */
    @Transactional
    public EventDTO createEvent(EventCreateDTO eventCreateDTO) {
        Event event = eventMapper.toEntity(eventCreateDTO);
        
        if (eventCreateDTO.getTicketIds() != null && !eventCreateDTO.getTicketIds().isEmpty()) {
            List<Ticket> tickets = ticketRepository.findAllById(eventCreateDTO.getTicketIds());
            event.setTickets(tickets);
        }
        
        Event savedEvent = eventRepository.save(event);
        return eventMapper.toDTO(savedEvent);
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
                    if (eventUpdateDTO.getTicketIds() != null) {
                        List<Ticket> tickets = ticketRepository.findAllById(eventUpdateDTO.getTicketIds());
                        existingEvent.setTickets(tickets);
                    }
                    
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