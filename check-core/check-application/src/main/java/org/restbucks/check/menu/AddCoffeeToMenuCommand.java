package org.restbucks.check.menu;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.UUID;

@Value(staticConstructor = "of")
public class AddCoffeeToMenuCommand {
    @NotNull private final UUID coffeeID;
    @NotBlank private final String coffeeCode;
    @NotNull @PositiveOrZero private final BigDecimal price;
    @NotBlank private final String currency;
}
