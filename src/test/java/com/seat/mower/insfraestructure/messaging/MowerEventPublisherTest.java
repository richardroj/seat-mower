package com.seat.mower.insfraestructure.messaging;

import com.seat.mower.insfraestructure.messaging.dto.MowerMessage;
import com.seat.mower.insfraestructure.messaging.dto.MowerUpdatedEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class MowerEventPublisherTest {

    private MowerEventPublisher mowerEventPublisher;

    @BeforeEach
    void setUp() {
        NewTopic topic = mock(NewTopic.class);
        KafkaTemplate<String, MowerUpdatedEvent> kafkaTemplate = mock(KafkaTemplate.class);
        mowerEventPublisher = new MowerEventPublisherImpl(topic, kafkaTemplate);
    }

    @Test
    void shouldPublishEvent() {
        //given
        var event = MowerUpdatedEvent.builder()
                .message("Subscription Test")
                .mowerMessage(MowerMessage.builder()
                        .id("1")
                        .orientation("E")
                        .position("X: 0, Y: 0")
                        .build()
                ).build();
        mowerEventPublisher = mock(MowerEventPublisherImpl.class);

        //when
        mowerEventPublisher.publish(event);

        //then
        verify(mowerEventPublisher, times(1)).publish(event);

    }

}
