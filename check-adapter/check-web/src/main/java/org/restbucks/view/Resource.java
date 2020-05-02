package org.restbucks.view;

import static java.util.Objects.requireNonNull;

enum Resource {
    CHECKS("checks", "checks"),
    COFFEES("coffees", "coffees");

    private String relation;
    private String path;

    Resource(String relation, String path) {
        this.relation = requireNonNull(relation);
        this.path = requireNonNull(path);
    }

    public String relation() {
        return relation;
    }
    public String path() {
        return path;
    }
}
