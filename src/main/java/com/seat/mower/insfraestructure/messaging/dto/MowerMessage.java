package com.seat.mower.insfraestructure.messaging.dto;

import com.seat.mower.domain.Orientation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class MowerMessage {
    private String id;
    String position;
    private String orientation;
}
