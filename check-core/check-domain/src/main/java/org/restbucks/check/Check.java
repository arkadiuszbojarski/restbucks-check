package org.restbucks.check;

import lombok.*;
import org.restbucks.Event;
import org.restbucks.check.event.CheckOpenedEvent;
import org.restbucks.check.event.CoffeeAddedToCheckEvent;
import org.restbucks.coffee.Coffee;
import org.restbucks.quantity.Quantity;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.Instant;
import java.util.List;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;
import static org.restbucks.check.event.CheckEventFactory.checkOpenedAt;
import static org.restbucks.check.event.CheckEventFactory.coffeeAddedToCheck;
import static org.restbucks.check.exception.CheckAlreadyOpenedException.checkAlreadyOpened;

@Getter(AccessLevel.NONE)
@Data(staticConstructor = "of")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false )
public class Check extends AbstractAggregateRoot<Check> {

    @Getter
    @NonNull
    @EqualsAndHashCode.Include
    private final CheckID checkID;
    private Instant openingTimestamp;

    static Check from(CheckID checkID, List<Event> events) {
        return io.vavr.collection.List.ofAll(events).foldLeft(Check.of(checkID), Check::accept);
    }

    private Check accept(Event event) {
        return Match(event).of(
                Case($(instanceOf(CheckOpenedEvent.class)), this::handle),
                Case($(instanceOf(CoffeeAddedToCheckEvent.class)), this::handle)
        );
    }

    List<Event> events() {
        return domainEvents().stream()
                .map(Event.class::cast)
                .collect(toList());
    }

    void clearEvents() {
        clearDomainEvents();
    }

    // Opening check
    private void requireNotAlreadyOpened() {
        if (this.isAlreadyOpened()) throw checkAlreadyOpened(checkID, openingTimestamp);
    }

    private boolean isAlreadyOpened() {
        return nonNull(openingTimestamp);
    }

    public void open(Instant openingTimestamp) {
        requireNotAlreadyOpened();
        final CheckOpenedEvent event = checkOpenedAt(checkID, openingTimestamp);
        registerEvent(event);
        handle(event);
    }

    private Check handle(CheckOpenedEvent event) {
        openingTimestamp = event.getTimestamp();
        return this;
    }

    // Adding coffee
    public void addCoffee(Quantity quantity, Coffee coffee, Instant timestamp) {
        final var event = coffeeAddedToCheck(checkID, coffee, quantity, timestamp);
        registerEvent(event);
        handle(event);
    }

    private Check handle(CoffeeAddedToCheckEvent event) {
        return this;
    }

}
