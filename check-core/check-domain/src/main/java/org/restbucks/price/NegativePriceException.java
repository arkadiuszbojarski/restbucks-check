package org.restbucks.price;

public class NegativePriceException extends RuntimeException {
    public NegativePriceException() {
        super("Price cannot be negative");
    }
}
