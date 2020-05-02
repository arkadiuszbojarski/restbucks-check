package org.restbucks.view;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.restbucks.check.ViewIntegration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

import static java.util.Objects.requireNonNull;
import static org.restbucks.view.Resource.CHECKS;
import static org.restbucks.view.Resource.COFFEES;

@Slf4j
@Component
@RequiredArgsConstructor
class EurekaViewIntegration implements ViewIntegration {
    private final LinkDiscoverer discoverer;
    private final ViewClient client;

    @Override
    public URI checkCoffeeLocation(UUID checkID, UUID coffeeID) {
        final URI checks = checksLocation();

        return UriComponentsBuilder
                .fromUri(checks)
                .pathSegment(requireNonNull(checkID).toString())
                .pathSegment(COFFEES.path())
                .pathSegment(requireNonNull(coffeeID).toString())
                .build()
                .toUri();
    }

    private URI checksLocation() {
        final var content = client.getLinks();

        return discoverer
                .findLinkWithRel(CHECKS.relation(), content)
                .get().toUri(); // TODO: handle missing link
    }
}
