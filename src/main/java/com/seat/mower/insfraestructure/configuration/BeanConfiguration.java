package com.seat.mower.insfraestructure.configuration;

import com.seat.mower.MowerApplication;
import com.seat.mower.domain.service.DomainMowerService;
import com.seat.mower.domain.service.MowerService;
import com.seat.mower.insfraestructure.messaging.MowerEventPublisher;
import com.seat.mower.insfraestructure.messaging.dto.MowerUpdatedEvent;
import com.seat.mower.insfraestructure.messaging.MowerEventPublisherImpl;
import com.seat.mower.insfraestructure.messaging.mapper.MowerUpdatedEventMapper;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
@ComponentScan(basePackageClasses = MowerApplication.class)
public class BeanConfiguration {


    @Bean
    MowerEventPublisher mowerEventPublisher(final NewTopic topic,
                                            KafkaTemplate<String,
                                                    MowerUpdatedEvent> kafkaTemplate) {
        return new MowerEventPublisherImpl(topic, kafkaTemplate);
    }

    @Bean
    MowerService mowerService(final MowerEventPublisher mowerEventPublisher, MowerUpdatedEventMapper mowerUpdatedEventMapper) {
        return new DomainMowerService(mowerEventPublisher, mowerUpdatedEventMapper);
    }

}
