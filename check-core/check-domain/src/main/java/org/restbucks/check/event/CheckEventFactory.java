package org.restbucks.check.event;

import org.restbucks.check.CheckID;
import org.restbucks.check.event.CheckOpenedEvent;
import org.restbucks.check.event.CoffeeAddedToCheckEvent;
import org.restbucks.coffee.Coffee;
import org.restbucks.coffee.CoffeeCode;
import org.restbucks.price.Price;
import org.restbucks.quantity.Quantity;

import java.math.BigDecimal;
import java.time.Instant;

import static java.util.UUID.randomUUID;

public enum CheckEventFactory {
    ;

    public static CoffeeAddedToCheckEvent coffeeAddedToCheck(CheckID checkID, Coffee coffee, Quantity quantity, Instant timestamp) {
        return CoffeeAddedToCheckEvent.of(
                randomUUID(),
                checkID.getValue(),
                coffee.getCoffeeID().getValue(),
                "latte", //coffee.getCoffeeCode(),// TODO: implement
                BigDecimal.ONE, //coffee.getPrice(),
                "PLN", //coffee.getCurrency(),
                quantity.getValue(),
                timestamp);
    }

    public static CheckOpenedEvent checkOpenedAt(CheckID checkID, Instant openingTimestamp) {
        return CheckOpenedEvent.of(
                randomUUID(),
                checkID.getValue(),
                openingTimestamp);
    }
}
