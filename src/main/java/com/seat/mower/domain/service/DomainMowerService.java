package com.seat.mower.domain.service;


import com.seat.mower.domain.Command;
import com.seat.mower.domain.Mower;
import com.seat.mower.domain.Orientation;
import com.seat.mower.insfraestructure.messaging.MowerEventPublisher;
import com.seat.mower.insfraestructure.messaging.dto.MowerUpdatedEvent;
import com.seat.mower.insfraestructure.messaging.mapper.MowerUpdatedEventMapper;

import java.awt.*;
import java.util.List;
import java.util.UUID;

public class DomainMowerService implements MowerService {

    private final MowerEventPublisher mowerEventPublisher;

    private final MowerUpdatedEventMapper mowerUpdatedEventMapper;

    public DomainMowerService(MowerEventPublisher mowerEventPublisher, MowerUpdatedEventMapper mowerUpdatedEventMapper){
        this.mowerEventPublisher = mowerEventPublisher;
        this.mowerUpdatedEventMapper = mowerUpdatedEventMapper;
    }

    @Override
    public Mower createMower(Point position, Orientation orientation) {
        return Mower.builder()
                .id(UUID.randomUUID()
                        .toString())
                .orientation(orientation)
                .position(position)
                .build();
    }

    @Override
    public Mower move(Mower mower, List<Command> commands) {
        mower.setCommands(commands);
        mower.executeCommands();
        MowerUpdatedEvent mowerUpdatedEvent = mowerUpdatedEventMapper.toSubscriptionCreatedEvent(mower);
        mowerEventPublisher.publish(mowerUpdatedEvent);
        return mower;
    }
}
