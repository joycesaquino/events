package com.events.tickets.repository;

import com.events.tickets.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing Event entities.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // Add custom query methods if needed
}