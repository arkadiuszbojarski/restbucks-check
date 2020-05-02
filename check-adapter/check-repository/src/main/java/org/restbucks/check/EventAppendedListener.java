package org.restbucks.check;

import lombok.RequiredArgsConstructor;
import org.restbucks.eventpublisher.EventPublisher;
import org.restbucks.eventstore.EventStore;
import org.restbucks.eventstore.event.AppendedEvent;
import org.restbucks.eventstore.event.Event;
import org.restbucks.eventstore.metadata.EventMetadata;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Transactional
@RequiredArgsConstructor
public class EventAppendedListener {
    private final EventPublisher publisher;
    private final EventStore store;

    @SuppressWarnings("unused")
    @TransactionalEventListener
    public void publish(AppendedEvent event) {
        final var eventID = event.getEventID();
        store.findEventByID(eventID)
                .map(Event::payload)
                .ifPresent(publisher::publish);

        store.updateEventMetadata(eventID, published());
    }

    private EventMetadata published() {
        return EventMetadata.builder()
                .attribute("published", true)
                .build();
    }

}
