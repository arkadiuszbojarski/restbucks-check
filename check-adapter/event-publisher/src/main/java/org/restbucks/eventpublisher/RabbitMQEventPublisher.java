package org.restbucks.eventpublisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.restbucks.DomainEventTypeExtractor;
import org.restbucks.eventpublisher.binding.OutputBinding;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.Objects.requireNonNull;
import static org.springframework.messaging.support.MessageBuilder.createMessage;

@Slf4j
@Component
@RequiredArgsConstructor
@EnableBinding(OutputBinding.class)
class RabbitMQEventPublisher implements EventPublisher {
    private final OutputBinding binding;

    @Override
    public void publish(Object event) {
        requireNonNull(event, "Domain event cannot be null");

        final var headers = createHeaders(event);
        final var message = createMessage(event, headers);

        binding.output().send(message);
    }

    private static MessageHeaders createHeaders(Object event) {
        final var header = EventPublisher.TYPE_HEADER;
        final String eventType = DomainEventTypeExtractor
                .type(event)
                .orElseThrow(); // TODO implement more meaningful exception

        return new MessageHeaders(Map.of(header, eventType));
    }

}
