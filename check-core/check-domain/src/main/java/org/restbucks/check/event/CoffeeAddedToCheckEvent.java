package org.restbucks.check.event;

import lombok.NonNull;
import lombok.Value;
import org.restbucks.DomainEvent;
import org.restbucks.Event;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Value(staticConstructor = "of")
@DomainEvent(type = "check.coffee.added")
public class CoffeeAddedToCheckEvent implements Event {
    @NonNull private final UUID eventID;
    @NonNull private final UUID checkID;
    @NonNull private final UUID coffeeID;
    @NonNull private final String coffeeCode;
    @NonNull private final BigDecimal price;
    @NonNull private final String currency;
    @NonNull private final Integer quantity;
    @NonNull private final Instant timestamp;
}
