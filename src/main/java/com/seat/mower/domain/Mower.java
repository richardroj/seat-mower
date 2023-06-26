package com.seat.mower.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.awt.*;
import java.util.List;


@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class Mower {
  private String id;
  private Point position;
  private Orientation orientation;
  private List<Command> commands;


  public void executeCommands(){
    commands.forEach(command -> {
      executeCommand(command);
    });
  }

  private void executeCommand(Command command){
    switch (command) {
      case L:
        moveToLeft();
        break;
      case R:
        moveToRight();
        break;
      case M:
        moveForward();
        break;
    }
  }

  private void moveToLeft(){
    switch (orientation) {
      case N:
        orientation = Orientation.W;
        break;
      case S:
        orientation = Orientation.E;
        break;
      case E:
        orientation = Orientation.N;
        break;
      case W:
        orientation = Orientation.S;
        break;
      default:
        throw new IllegalArgumentException("Orientation not accepted");
    }

  }

  private void moveToRight(){
    switch (orientation) {
      case N:
        orientation = Orientation.E;
        break;
      case S:
        orientation = Orientation.W;
        break;
      case E:
        orientation = Orientation.S;
        break;
      case W:
        orientation = Orientation.N;
        break;
      default:
        throw new IllegalArgumentException("Direction not accepted");
    }
  }

  private void moveForward(){
    switch (orientation){

      case N:
        position.setLocation(position.getX(), position.getY() + 1);
        break;
      case S:
        position.setLocation(position.getX(), position.getY() - 1);
        break;
      case E:
        position.setLocation(position.getX() + 1, position.getY());
        break;
      case W:
        position.setLocation(position.getX() -1 , position.getY());
        break;
    }
  }

  public String toString(){
    return String.format("Mower with %s %s %s", position.getX(), position.getY(), orientation.toString());
  }

}
