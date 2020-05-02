package org.restbucks.check;

import lombok.RequiredArgsConstructor;
import org.restbucks.eventstore.EventStore;
import org.restbucks.eventstore.event.Event;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Repository
@RequiredArgsConstructor
class EventStoreFindCheckRepository implements FindCheckRepository {
    private final EventStore store;

    @Override
    public Optional<Check> findByID(CheckID checkID) {
        final var events = store
                .findEventsOfStream(checkID.getValue()).stream()
                .map(Event::payload)
                .map(org.restbucks.Event.class::cast)
                .collect(toList());

        return Optional.of(Check.from(checkID, events));
    }
}