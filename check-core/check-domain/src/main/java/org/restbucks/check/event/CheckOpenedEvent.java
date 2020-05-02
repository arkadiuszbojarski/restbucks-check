package org.restbucks.check.event;

import lombok.NonNull;
import lombok.Value;
import org.restbucks.DomainEvent;
import org.restbucks.Event;

import java.time.Instant;
import java.util.UUID;

@Value(staticConstructor = "of")
@DomainEvent(type = "check.opened")
public class CheckOpenedEvent implements Event {
    @NonNull private final UUID eventID;
    @NonNull private final UUID checkID;
    @NonNull private final Instant timestamp;
}
