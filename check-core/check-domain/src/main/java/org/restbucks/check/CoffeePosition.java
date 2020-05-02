package org.restbucks.check;

import lombok.Value;
import org.restbucks.coffee.CoffeeCode;
import org.restbucks.coffee.CoffeeID;
import org.restbucks.price.Price;
import org.restbucks.quantity.Quantity;

@Value(staticConstructor = "of")
public final class CoffeePosition {
    private final CoffeeID coffeeID;
    private final CoffeeCode coffeeCode;
    private final Price price;
    private final Quantity quantity;

    public CoffeePosition increaseQuantity(Quantity quantity) {
        return CoffeePosition.of(coffeeID, coffeeCode, price, this.quantity.add(quantity));
    }
}
