package org.restbucks.check;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value(staticConstructor = "of")
public final class CheckID {
    @NonNull private final UUID value;
}
