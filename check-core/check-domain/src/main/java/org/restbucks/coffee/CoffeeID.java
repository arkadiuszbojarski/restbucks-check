package org.restbucks.coffee;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value(staticConstructor = "of")
public final class CoffeeID {
    @NonNull
    private final UUID value;
}
