package org.restbucks.check.adding.coffee;

import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

@Value(staticConstructor = "of")
public final class AddPickedCoffeeToCustomerCheckCommand {
    @NotNull private final UUID checkID;
    @NotNull private final UUID coffeeID;
    @NotNull @Positive private final Integer quantity;
}
