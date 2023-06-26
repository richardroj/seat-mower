package com.seat.mower.domain.service;

import com.seat.mower.domain.Command;
import com.seat.mower.domain.Mower;
import com.seat.mower.domain.Orientation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DomainMowerServiceTest {
    @Autowired
    MowerService mowerService;

    @Test
    void createMower() {
        //given
        Point point = new Point(2, 2);
        Orientation orientation = Orientation.E;

        //when
        var response = mowerService.createMower(point, orientation);

        //then
        assertThat(response.getPosition()).isEqualTo(point);
        assertThat(response.getOrientation()).isEqualTo(orientation);
        assertThat(response.getId()).isNotEmpty();
    }

    @Test
    void move() {
        //given
        Point point = new Point(2, 2);
        Orientation orientation = Orientation.E;
        var mower = Mower.builder()
                .position(point)
                .orientation(orientation)
                .build();

        //when
        var response = mowerService.move(mower, List.of(Command.L, Command.M));

        //then
        assertThat(response.getOrientation()).isEqualTo(Orientation.N);
        assertThat(response.getPosition()).isEqualTo(new Point(2,3));
    }
}