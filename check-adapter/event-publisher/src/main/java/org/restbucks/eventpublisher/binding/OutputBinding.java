package org.restbucks.eventpublisher.binding;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OutputBinding {

    String OUTPUT = "check-output";

    @Output(OUTPUT)
    MessageChannel output();
}
