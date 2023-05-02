package com.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ElevatorFloorsTest {
  @Test
  public void howManyButtons() {
    assertEquals(0, ElevatorFloors.howManyButtons(new int[]{0}, 0, 0));
    assertEquals(1, ElevatorFloors.howManyButtons(new int[]{
      2, 3, 1, 5, 4
    }, 0, 2));
    assertEquals(2, ElevatorFloors.howManyButtons(new int[]{
      2, 3, 1, 5, 4
    }, 0, 1));
    assertEquals(3, ElevatorFloors.howManyButtons(new int[]{
      2, 3, 1, 5, 4
    }, 0, 4));
  }
}
