package org.restbucks.check.exception;

import org.restbucks.check.CheckID;

import java.time.Instant;

import static java.lang.String.format;

public class CheckAlreadyOpenedException extends RuntimeException {
    public static final String MESSAGE_TEMPLATE = "Check with id: %s already opened at: %s";

    private CheckAlreadyOpenedException(CheckID checkID, Instant openedAt) {
        super(format(MESSAGE_TEMPLATE, checkID, openedAt));
    }

    public static CheckAlreadyOpenedException checkAlreadyOpened(CheckID checkID, Instant openingTimestamp) {
        return new CheckAlreadyOpenedException(checkID, openingTimestamp);
    }
}
