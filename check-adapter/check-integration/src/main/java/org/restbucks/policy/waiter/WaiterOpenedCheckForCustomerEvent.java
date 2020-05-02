package org.restbucks.policy.waiter;

import lombok.Value;

import java.time.Instant;
import java.util.UUID;

@Value(staticConstructor = "of")
public class WaiterOpenedCheckForCustomerEvent {
    private final UUID eventID;
    private final UUID checkID;
    private final Instant timestamp;
    private final String type = "waiter.check.opened";
}
