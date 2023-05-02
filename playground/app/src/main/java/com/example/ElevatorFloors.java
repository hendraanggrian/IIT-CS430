package com.example;

import javafx.util.Pair;

import java.util.*;

public class ElevatorFloors {
  private static final int UNREACHABLE = -1;

  public static int howManyButtons(int[] k, int a, int b) {
    return howManyButtons(k, a, b, k.length);
  }

  public static int howManyButtons(int[] k, int a, int b, int n) {
    if (a == b) {
      return 0;
    }
    Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
    queue.offer(new Pair<>(a, 0));
    Set<Integer> visited = new HashSet<>();
    while (!queue.isEmpty()) {
      Pair<Integer, Integer> current = queue.poll();
      int currentFloor = current.getKey();
      int currentPresses = current.getValue();
      if (currentFloor == b) {
        return currentPresses;
      }
      visited.add(currentFloor);
      int move = k[currentFloor];
      if (currentFloor >= move && currentFloor - move < n &&
        !visited.contains(currentFloor - move)) {
        queue.offer(new Pair<>(currentFloor - move, currentPresses + 1));
      }
      if (currentFloor + move < n && currentFloor + move > 1 &&
        !visited.contains(currentFloor + move)) {
        queue.offer(new Pair<>(currentFloor + move, currentPresses + 1));
      }
    }
    return UNREACHABLE;
  }
}
