package org.restbucks;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public interface Event extends Serializable {
    UUID getEventID();
    Instant getTimestamp();
}
