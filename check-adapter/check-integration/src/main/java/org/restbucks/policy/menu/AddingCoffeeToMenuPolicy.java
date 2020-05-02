package org.restbucks.policy.menu;

import lombok.RequiredArgsConstructor;
import org.restbucks.check.CheckFacade;
import org.restbucks.check.menu.AddCoffeeToMenuCommand;
import org.restbucks.eventpublisher.binding.InputBinding;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(InputBinding.class)
@RequiredArgsConstructor
public class AddingCoffeeToMenuPolicy {
    private final CheckFacade facade;

    @StreamListener(value = InputBinding.INPUT, condition = "headers['type']=='menu.coffee.added'")
    public void handle(final CoffeeAddedToMenuEvent event) {
        final var coffeeID = event.getCoffeeID();
        final var coffeeCode = event.getCoffeeCode();
        final var price = event.getPrice();
        final var currency = event.getCurrency();
        final var command = AddCoffeeToMenuCommand.of(coffeeID, coffeeCode, price, currency);

        facade.addCoffeeToMenu(command);
    }
}
