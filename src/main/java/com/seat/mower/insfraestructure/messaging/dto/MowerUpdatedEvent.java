package com.seat.mower.insfraestructure.messaging.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MowerUpdatedEvent {
    private String message;
    private MowerMessage mowerMessage;

}
