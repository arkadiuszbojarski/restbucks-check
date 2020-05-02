package org.restbucks.quantity;

import lombok.*;

import static java.util.Objects.requireNonNull;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class Quantity {
    private final int value;

    public static Quantity of(Integer value) {
        requireNonNull(value);
        requirePositive(value);

        return new Quantity(value);
    }

    private static void requirePositive(Integer value) {
        if (value <= 0) throw new NonPositiveQuantityException();
    }

    public Quantity add(Quantity quantity) {
        return of(quantity.getValue() + getValue());
    }

}
