package org.restbucks.check.openning;

import lombok.RequiredArgsConstructor;
import org.restbucks.check.CheckID;
import org.restbucks.check.FindCheckRepository;
import org.restbucks.check.SaveCheckRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;

@Service
@Transactional
@RequiredArgsConstructor
public class OpeningCheckHandler {
    private final FindCheckRepository findCheckRepository;
    private final SaveCheckRepository saveCheckRepository;
    private final Clock clock;

    public void handler(OpenCustomerCheckCommand command) {
        final var checkID = CheckID.of(command.getCheckID());
        final var check = findCheckRepository.findByID(checkID).orElseThrow();

        check.open(Instant.now(clock));

        saveCheckRepository.save(check);
    }
}
