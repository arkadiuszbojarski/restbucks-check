package org.restbucks.check;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.restbucks.check.adding.coffee.AddPickedCoffeeToCustomerCheckCommand;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.Objects;
import java.util.UUID;

import static org.restbucks.check.PathAndBodyMismatchException.checkIDMismatch;
import static org.restbucks.check.PathAndBodyMismatchException.coffeeIDMismatch;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CheckController {
    private final ViewIntegration integration;
    private final CheckFacade facade;

    @PutMapping(value = "/checks/{checkID}/coffees/{coffeeID}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCoffeeToCheck(
            @PathVariable("checkID") UUID checkID,
            @PathVariable("coffeeID") UUID coffeeID,
            @Valid @RequestBody AddPickedCoffeeToCustomerCheckCommand command) {
        if (!match(checkID, command.getCheckID())) throw checkIDMismatch(checkID, command.getCheckID());
        if (!match(coffeeID, command.getCoffeeID())) throw coffeeIDMismatch(coffeeID, command.getCoffeeID());

        facade.addCoffeeToCheck(command);

        return ResponseEntity
                .created(coffeeLocation(checkID, coffeeID))
                .build();
    }

    private URI coffeeLocation(UUID checkID, UUID coffeeID) {
        return integration.checkCoffeeLocation(checkID, coffeeID);
    }

    private static boolean match(UUID pathID, UUID commandID) {
        return Objects.equals(pathID, commandID);
    }
}
