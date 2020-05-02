package org.restbucks.price;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.javamoney.moneta.Money;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.util.Objects.requireNonNull;

@Value
@RequiredArgsConstructor
public final class Price {
    @NonNull private final Money money;

    public static Price of(BigDecimal value, String currency) {
        requireNonNull(value);
        requireNonNull(currency);
        requireNonNegative(value);

        return new Price(Money.of(value, currency));
    }

    private static void requireNonNegative(BigDecimal value) {
        if (value.compareTo(ZERO) < 0) throw new NegativePriceException();
    }
}
