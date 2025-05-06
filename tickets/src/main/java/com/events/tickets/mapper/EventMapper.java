package com.events.tickets.mapper;

import com.events.tickets.cep.ViaCepResponse;
import com.events.tickets.dto.EventCreateDTO;
import com.events.tickets.dto.EventDTO;
import com.events.tickets.dto.EventUpdateDTO;
import com.events.commons.entity.Event;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between Event entity and DTOs.
 * Uses Java's Function interface for mapping operations.
 */
@Component
public class EventMapper {

    /**
     * Function to convert Event entity to EventDTO.
     */
    private final Function<Event, EventDTO> eventToDtoMapper = event -> {
        if (event == null) {
            return null;
        }

        EventDTO dto = new EventDTO();
        dto.setId(event.getId());
        dto.setStatus(event.getStatus());
        dto.setCity(event.getCity());
        dto.setState(event.getState());
        dto.setStreet(event.getStreet());
        dto.setNeighborhood(event.getNeighborhood());
        dto.setZipCode(event.getZipCode());
        dto.setCreatedAt(event.getCreatedAt());
        dto.setUpdatedAt(event.getUpdatedAt());
        return dto;
    };

    /**
     * Convert Event entity to EventDTO.
     *
     * @param event the event entity
     * @return the event DTO
     */
    public EventDTO toDTO(Event event) {
        return eventToDtoMapper.apply(event);
    }

    /**
     * Convert a list of Event entities to a list of EventDTOs.
     *
     * @param events the list of event entities
     * @return the list of event DTOs
     */
    public List<EventDTO> toDTOList(List<Event> events) {
        if (events == null) {
            return null;
        }
        return events.stream()
                .map(eventToDtoMapper)
                .collect(Collectors.toList());
    }

    /**
     * Convert EventCreateDTO to Event entity.
     * Note: This method does not set the Ticket entities, they should be set separately.
     *
     * @param eventCreateDTO the event create DTO
     * @return the event entity
     */
    public Event toEntity(EventCreateDTO eventCreateDTO, ViaCepResponse address) {
        if (eventCreateDTO == null) {
            return null;
        }

        Event event = new Event();
        event.setStatus(eventCreateDTO.getStatus());
        event.setCity(address.getLocalidade());
        event.setState(address.getUf());
        event.setStreet(address.getLogradouro());
        event.setNeighborhood(address.getBairro());
        event.setZipCode(eventCreateDTO.getZipCode());
        return event;
    }

    /**
     * Update Event entity from EventUpdateDTO.
     * Note: This method does not update the Ticket entities, they should be updated separately.
     *
     * @param eventUpdateDTO the event update DTO
     * @param event the event entity to update
     * @return the updated event entity
     */
    public Event updateEventFromDTO(EventUpdateDTO eventUpdateDTO, Event event) {
        if (eventUpdateDTO == null || event == null) {
            return event;
        }

        event.setStatus(eventUpdateDTO.getStatus());
        return event;
    }
}
