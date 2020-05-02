package org.restbucks.eventpublisher;

public interface EventPublisher {
    String TYPE_HEADER = "type";

    void publish(Object event);
}
