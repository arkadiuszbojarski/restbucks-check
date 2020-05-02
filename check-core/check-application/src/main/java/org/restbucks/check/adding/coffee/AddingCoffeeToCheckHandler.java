package org.restbucks.check.adding.coffee;

import lombok.RequiredArgsConstructor;
import org.restbucks.check.CheckID;
import org.restbucks.check.FindCheckRepository;
import org.restbucks.check.SaveCheckRepository;
import org.restbucks.coffee.Coffee;
import org.restbucks.coffee.CoffeeID;
import org.restbucks.coffee.CoffeeRepository;
import org.restbucks.quantity.Quantity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;

import static org.restbucks.coffee.CoffeeNotFoundException.coffeeNotFound;

@Service
@Transactional
@RequiredArgsConstructor
public class AddingCoffeeToCheckHandler {
    private final CoffeeRepository coffeeRepository;
    private final FindCheckRepository findCheckRepository;
    private final SaveCheckRepository saveCheckRepository;
    private final Clock clock;

    public void handle(AddPickedCoffeeToCustomerCheckCommand command) {
        final var check = findCheckRepository.findByID(CheckID.of(command.getCheckID())).orElseThrow();
        final var coffee = findCoffee(CoffeeID.of(command.getCoffeeID()));
        final var quantity = Quantity.of(command.getQuantity());

        check.addCoffee(quantity, coffee, timestamp());

        saveCheckRepository.save(check);
    }

    private Coffee findCoffee(CoffeeID coffeeID) {
        return coffeeRepository
                .findByID(coffeeID)
                .orElseThrow(() -> coffeeNotFound(coffeeID));
    }

    private Instant timestamp() {
        return Instant.now(clock);
    }
}