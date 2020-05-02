package org.restbucks.check;

import lombok.RequiredArgsConstructor;
import org.restbucks.eventstore.EventStore;
import org.restbucks.eventstore.event.Event;
import org.restbucks.eventstore.metadata.EventMetadata;
import org.springframework.stereotype.Repository;

import static java.util.Objects.requireNonNull;

@Repository
@RequiredArgsConstructor
public class EventStoreSaveCheckRepository implements SaveCheckRepository {
    private final EventStore store;

    @Override
    public void save(Check check) {
        final var checkID = requireNonNull(check).getCheckID();
        final var uuid = checkID.getValue();

        check.events().forEach(event -> store.appendEventToStream(uuid, prepareEvent(event)));

        check.clearEvents();
    }

    private Event<org.restbucks.Event> prepareEvent(org.restbucks.Event event) {
        final var metadata = EventMetadata.builder()
                .eventID(event.getEventID())
                .timestamp(event.getTimestamp())
                .attribute("published", false)
                .build();

        return Event.createEvent(event, metadata);
    }
}
