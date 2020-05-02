package org.restbucks.check;

import lombok.RequiredArgsConstructor;
import org.restbucks.eventpublisher.EventPublisher;
import org.restbucks.eventstore.EventStore;
import org.restbucks.eventstore.event.Event;
import org.restbucks.eventstore.metadata.EventMetadata;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class EventRepublicationSchedule {
    private final EventPublisher publisher;
    private final EventStore store;

    @Scheduled(fixedDelay = 30000)
    public void publish() {
        store.findEventsWithMetadata(published(false))
                .stream()
                .forEach(this::publish);
    }

    private void publish(Event event) {
        final var payload = event.payload();
        publisher.publish(payload);

        final var eventID = event.metadata().getEventID();

        store.updateEventMetadata(eventID, published(true));
    }

    private EventMetadata published(boolean published) {
        return EventMetadata.builder()
                .attribute("published", published)
                .build();
    }

}
