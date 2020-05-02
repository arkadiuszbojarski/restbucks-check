package org.restbucks.check;

import lombok.RequiredArgsConstructor;
import org.restbucks.check.adding.coffee.AddPickedCoffeeToCustomerCheckCommand;
import org.restbucks.check.adding.coffee.AddingCoffeeToCheckHandler;
import org.restbucks.check.menu.AddCoffeeToMenuCommand;
import org.restbucks.check.openning.OpeningCheckHandler;
import org.restbucks.check.openning.OpenCustomerCheckCommand;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.requireNonNull;

@Component
@Transactional
@RequiredArgsConstructor
public class CheckFacade {
    private final AddingCoffeeToCheckHandler addingCoffeeToCheckHandler;
    private final OpeningCheckHandler openingCheckHandler;

    public void addCoffeeToCheck(AddPickedCoffeeToCustomerCheckCommand command) {
        requireNonNull(command);
        addingCoffeeToCheckHandler.handle(command);
    }

    public void openCheck(OpenCustomerCheckCommand command) {
        requireNonNull(command);
        openingCheckHandler.handler(command);
    }

    public void addCoffeeToMenu(AddCoffeeToMenuCommand command) {

    }

}
