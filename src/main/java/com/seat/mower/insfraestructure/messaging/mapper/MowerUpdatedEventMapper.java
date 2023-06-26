package com.seat.mower.insfraestructure.messaging.mapper;

import com.seat.mower.domain.Mower;
import com.seat.mower.insfraestructure.messaging.dto.MowerMessage;
import com.seat.mower.insfraestructure.messaging.dto.MowerUpdatedEvent;
import org.springframework.stereotype.Component;

@Component
public class MowerUpdatedEventMapper {
    public MowerUpdatedEvent toSubscriptionCreatedEvent(Mower mower) {
        return MowerUpdatedEvent.builder()
                .message("Mower Updated")
                .mowerMessage(MowerMessage.builder()
                        .id(mower.getId())
                        .position("X:" + mower.getPosition()
                                .getX() + ", Y: " + mower.getPosition()
                                .getY())
                        .orientation(mower.getOrientation()
                                .toString())
                        .build())
                .build();
    }
}
