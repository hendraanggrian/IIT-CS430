package com.example.maze;

public class GasStationMazes {
  public static final char NOTHING = ' ';
  public static final char STATION = '*';
  public static final int A = 3;
  public static final int B = 1;
  public static final int C = 5;

  private GasStationMazes() {
  }

  public static int getSpent(char[][] maze, int s, int d, int maxTank) {
    return getSpent(maze, s, s, d, d, maxTank, maxTank, 0);
  }

  public static int getSpent(
    char[][] maze, int sx, int sy, int dx, int dy, int maxTank, int tank, int spent
  ) {
    if (sx == dx && sy == dy) {
      return spent;
    } else if (sx + 1 > dx || sy + 1 > dy) {
      return spent;
    }
    if (maze[sx][sy] == STATION && tank < maxTank) {
      spent += A;
      tank = maxTank;
    }
    if (tank == 0) {
      int min = getSpent(maze, sx, sy + 1, dx, dy, maxTank, maxTank, spent + C + A);
      min = Math.min(min, getSpent(maze, sx + 1, sy, dx, dy, maxTank, tank, spent + B));
      min = Math.min(min, getSpent(maze, sx, sy + 1, dx, dy, maxTank, maxTank, spent + C + A));
      min = Math.min(min, getSpent(maze, sx, sy + 1, dx, dy, maxTank, tank, spent + B));
      return min;
    }
    return Math.max(
      getSpent(maze, sx + 1, sy, dx, dy, maxTank, tank - 1, spent),
      getSpent(maze, sx, sy + 1, dx, dy, maxTank, tank - 1, spent)
    );
  }
}
