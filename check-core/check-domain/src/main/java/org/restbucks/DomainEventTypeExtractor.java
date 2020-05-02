package org.restbucks;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

public enum DomainEventTypeExtractor {
    ;

    public static Optional<String> type(final Object event) {
        requireNonNull(event);
        return type(event.getClass());
    }

    public static <T> Optional<String> type(final Class<T> tClass) {
        requireNonNull(tClass);
        final var type = Optional
                .ofNullable(tClass)
                .map(c -> c.getAnnotation(DomainEvent.class))
                .map(DomainEvent::type);

        return type;
    }
}
