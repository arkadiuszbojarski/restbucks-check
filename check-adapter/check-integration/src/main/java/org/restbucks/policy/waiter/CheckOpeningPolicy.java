package org.restbucks.policy.waiter;

import lombok.RequiredArgsConstructor;
import org.restbucks.check.CheckFacade;
import org.restbucks.check.openning.OpenCustomerCheckCommand;
import org.restbucks.eventpublisher.binding.InputBinding;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(InputBinding.class)
@RequiredArgsConstructor
public class CheckOpeningPolicy {
    private final CheckFacade facade;

    @StreamListener(value = InputBinding.INPUT, condition = "headers['type']=='waiter.check.opened'")
    public void handle(final WaiterOpenedCheckForCustomerEvent event) {
        final var command = OpenCustomerCheckCommand.of(event.getCheckID());

        facade.openCheck(command);
    }
}
