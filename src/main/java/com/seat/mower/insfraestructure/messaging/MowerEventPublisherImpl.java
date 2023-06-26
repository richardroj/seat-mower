package com.seat.mower.insfraestructure.messaging;

import com.seat.mower.insfraestructure.messaging.dto.MowerUpdatedEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class MowerEventPublisherImpl implements MowerEventPublisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(MowerEventPublisherImpl.class);

    private final NewTopic topic;

    private final KafkaTemplate<String, MowerUpdatedEvent> kafkaTemplate;

    public MowerEventPublisherImpl(NewTopic topic, KafkaTemplate<String,
            MowerUpdatedEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(MowerUpdatedEvent mowerUpdatedEvent) {
        LOGGER.info(format("Mower event => %s", mowerUpdatedEvent.toString()));
        // create Message
        Message<MowerUpdatedEvent> message = MessageBuilder
            .withPayload(mowerUpdatedEvent)
            .setHeader(KafkaHeaders.TOPIC, topic.name())
            .build();
        kafkaTemplate.send(message);
    }

}
