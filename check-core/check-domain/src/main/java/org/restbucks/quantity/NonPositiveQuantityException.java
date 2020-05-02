package org.restbucks.quantity;

public class NonPositiveQuantityException extends RuntimeException {
    public NonPositiveQuantityException() {
        super("Quantity not greater than zero");
    }
}
