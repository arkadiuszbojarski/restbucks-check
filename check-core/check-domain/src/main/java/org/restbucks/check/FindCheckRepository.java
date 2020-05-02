package org.restbucks.check;

import java.util.Optional;

public interface FindCheckRepository {
    Optional<Check> findByID(CheckID checkID);
}
