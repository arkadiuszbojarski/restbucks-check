package org.restbucks.check;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

import static java.lang.String.format;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Some parameters mismatch in path and body")
public class PathAndBodyMismatchException extends RuntimeException {

    private PathAndBodyMismatchException(String field, UUID path, UUID body) {
        super(format("%s in path %s mismatch %s in body %s", field, path, field, body));
    }

    public static PathAndBodyMismatchException checkIDMismatch(UUID patchCheckID, UUID commandCheckID) {
        return new PathAndBodyMismatchException("checkID", patchCheckID, commandCheckID);
    }

    public static PathAndBodyMismatchException coffeeIDMismatch(UUID patchCoffeeID, UUID commandCoffeeID) {
        return new PathAndBodyMismatchException("coffee", patchCoffeeID, commandCoffeeID);
    }

}
