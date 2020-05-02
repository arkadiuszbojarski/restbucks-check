package org.restbucks.check;

import org.restbucks.DomainEvent;
import org.restbucks.eventstore.jpa.mapping.annotation.AnnotationClassMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import static org.restbucks.eventstore.jpa.mapping.annotation.AnnotationClassMapper.annotationTypeMapper;

@Configuration
@EnableScheduling
public class EventStoreCheckRepositoryConfiguration {

    @Bean
    public AnnotationClassMapper<DomainEvent, String> domainEventAnnotationMapper() {
        return annotationTypeMapper(DomainEvent.class, DomainEvent::type);
    }

}
