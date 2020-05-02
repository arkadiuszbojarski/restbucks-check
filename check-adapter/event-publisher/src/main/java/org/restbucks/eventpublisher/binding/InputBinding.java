package org.restbucks.eventpublisher.binding;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface InputBinding {

    String INPUT = "check-input";

    @Input(INPUT)
    SubscribableChannel input();
}
