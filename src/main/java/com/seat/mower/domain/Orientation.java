package com.seat.mower.domain;

import java.util.Arrays;

public enum Orientation {
  N, S, E, W;
  public static boolean contains(String testedValue) {
    return Arrays.stream(values()).map(Enum::name).anyMatch(point -> point.equals(testedValue));
  }
}
