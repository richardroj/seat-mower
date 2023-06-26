package com.seat.mower.insfraestructure.messaging;

import com.seat.mower.insfraestructure.messaging.dto.MowerUpdatedEvent;

public interface MowerEventPublisher {

    void publish(MowerUpdatedEvent mowerUpdatedEvent);
}
