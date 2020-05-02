package org.restbucks.coffee;

import lombok.NonNull;
import lombok.Value;

@Value(staticConstructor = "of")
public final class CoffeeCode {
    @NonNull
    private final String value;
}
