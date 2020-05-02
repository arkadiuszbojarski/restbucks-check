package org.restbucks.check;

import org.restbucks.coffee.Coffee;
import org.restbucks.coffee.CoffeeID;
import org.restbucks.coffee.CoffeeRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

@Repository
public class InMemoryCoffeeRepository implements CoffeeRepository {

    @Override
    public Optional<Coffee> findByID(CoffeeID coffeeID) {
        return Optional.of(Coffee.of(coffeeID));
    }

    @Override
    public void save(Coffee coffee) {

    }
}
