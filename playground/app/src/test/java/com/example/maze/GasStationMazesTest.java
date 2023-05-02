package com.example.maze;

import org.junit.Test;

import static com.example.maze.GasStationMazes.NOTHING;
import static com.example.maze.GasStationMazes.STATION;
import static org.junit.Assert.assertEquals;

public class GasStationMazesTest {
  @Test
  public void getSpent() {
    char[][] maze = new char[5][5];
    for (int i = 0; i < maze.length; i++) {
      for (int j = 0; j < maze[0].length; j++) {
        maze[i][j] = NOTHING;
      }
    }
    maze[1][1] = STATION;
    maze[3][3] = STATION;
    maze[4][4] = STATION;
    assertEquals(6, GasStationMazes.getSpent(maze, 0, 4, 4));
  }
}
