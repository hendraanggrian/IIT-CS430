package com.example.maze;

import org.junit.Test;

import static com.example.maze.ReachableMazes.REACHABLE;
import static com.example.maze.ReachableMazes.UNREACHABLE;

public class ReachableMazesTest {
  @Test
  public void getMinMoves() {
    char[][] maze = new char[10][6];
    for (int i = 0; i < maze.length; i++) {
      for (int j = 0; j < maze[0].length; j++) {
        maze[i][j] = REACHABLE;
      }
    }
    maze[4][0] = UNREACHABLE;
    maze[6][0] = UNREACHABLE;
    maze[7][0] = UNREACHABLE;
    maze[1][1] = UNREACHABLE;
    maze[3][1] = UNREACHABLE;
    maze[5][1] = UNREACHABLE;
    maze[0][2] = UNREACHABLE;
    maze[1][2] = UNREACHABLE;
    maze[2][2] = UNREACHABLE;
    maze[3][2] = UNREACHABLE;
    maze[4][2] = UNREACHABLE;
    maze[9][2] = UNREACHABLE;
    maze[0][3] = UNREACHABLE;
    maze[1][3] = UNREACHABLE;
    maze[0][4] = UNREACHABLE;
    maze[5][4] = UNREACHABLE;
    maze[9][4] = UNREACHABLE;
    maze[8][5] = UNREACHABLE;
    maze[9][5] = UNREACHABLE;
//    assertEquals(5, MazeGraphs.getMinMoves(maze, 2, 3, 6, 2));
  }
}
