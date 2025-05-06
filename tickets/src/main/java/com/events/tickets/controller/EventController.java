package com.events.tickets.controller;

import com.events.tickets.dto.EventCreateDTO;
import com.events.tickets.dto.EventDTO;
import com.events.tickets.dto.EventUpdateDTO;
import com.events.tickets.facade.EventFacade;
import com.events.tickets.service.EventService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for event operations.
 */
@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventFacade eventFacade;

    /**
     * Create a new event.
     *
     * @param eventCreateDTO the event data to create
     * @return the created event DTO
     */
    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody @Valid EventCreateDTO eventCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(eventFacade.createEvent(eventCreateDTO));
    }

    /**
     * Get all events.
     *
     * @return list of all events as DTOs
     */
    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    /**
     * Get an event by ID.
     *
     * @param id the event ID
     * @return the event DTO if found, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Update an existing event.
     *
     * @param id the event ID
     * @param eventUpdateDTO the event data to update
     * @return the updated event DTO if found, or 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Long id, @RequestBody EventUpdateDTO eventUpdateDTO) {
        return eventService.updateEvent(id, eventUpdateDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete an event by ID.
     *
     * @param id the event ID
     * @return 204 No Content if successful, 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        if (eventService.getEventById(id).isPresent()) {
            eventService.deleteEvent(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}