package com.seat.mower.domain.service;

import com.seat.mower.domain.Command;
import com.seat.mower.domain.Mower;
import com.seat.mower.domain.Orientation;

import java.awt.*;
import java.util.List;

public interface MowerService {

    Mower createMower(Point position, Orientation orientation);

    Mower move(Mower mower, List<Command> commands);
}
