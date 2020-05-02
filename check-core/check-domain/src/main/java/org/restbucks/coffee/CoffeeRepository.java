package org.restbucks.coffee;

import java.util.Optional;

public interface CoffeeRepository {
    Optional<Coffee> findByID(CoffeeID coffeeID);

    void save(Coffee coffee);
}
