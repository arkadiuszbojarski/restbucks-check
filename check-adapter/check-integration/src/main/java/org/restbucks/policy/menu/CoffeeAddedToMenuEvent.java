package org.restbucks.policy.menu;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Value(staticConstructor = "of")
public class CoffeeAddedToMenuEvent implements Serializable {
    private final UUID eventID;
    private final UUID coffeeID;
    private final String coffeeCode;
    private final BigDecimal price;
    private final String currency;
    private final Instant timestamp;
}
