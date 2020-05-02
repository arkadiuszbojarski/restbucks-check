package org.restbucks.coffee;

import lombok.Value;

@Value(staticConstructor = "of")
public class Coffee {
    private final CoffeeID coffeeID;
}
