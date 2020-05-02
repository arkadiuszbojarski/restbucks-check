package org.restbucks.check;

import java.net.URI;
import java.util.UUID;

public interface ViewIntegration {
    URI checkCoffeeLocation(UUID checkID, UUID coffeeID);
}
