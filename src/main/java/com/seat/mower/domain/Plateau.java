package com.seat.mower.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Plateau {

  private String id;
  private Integer width;
  private Integer height;

  private List<Mower> mowers;

  @Override
  public String toString(){
    return String.format("Id %s width %s height %s mowers %s", id, width, height.intValue(),mowers);
  }
}
