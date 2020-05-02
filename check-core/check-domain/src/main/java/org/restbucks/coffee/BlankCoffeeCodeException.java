package org.restbucks.coffee;

public class BlankCoffeeCodeException extends RuntimeException {
    public BlankCoffeeCodeException() {
        super("Blank coffee code");
    }
}
