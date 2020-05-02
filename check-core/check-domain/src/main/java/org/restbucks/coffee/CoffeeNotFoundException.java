package org.restbucks.coffee;

import java.util.UUID;

import static java.lang.String.format;

public class CoffeeNotFoundException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Coffee with id: %s not found in menu";

    public static CoffeeNotFoundException coffeeNotFound(CoffeeID coffeeID) {
        return new CoffeeNotFoundException(coffeeID.getValue());
    }

    private CoffeeNotFoundException(UUID coffeeID) {
        super(format(MESSAGE_TEMPLATE, coffeeID));
    }
}
