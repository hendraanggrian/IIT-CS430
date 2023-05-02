package com.example;

import java.util.Arrays;

public class CellTowerPlacements {
  private static final int COVERAGE = 1; // in miles

  private CellTowerPlacements() {
  }

  public static int[] minLocations(int... X) {
    return minLocations(X, X.length);
  }

  public static int[] minLocations(int[] X, int d) {
    if (d == 0) {
      return new int[0];
    } else if (d == 1) {
      return new int[]{X[0] + COVERAGE};
    }
    Arrays.sort(X);
    int[] Y = new int[d];
    Y[0] = X[0] + COVERAGE;
    int j = 0;
    for (int i = 1; i < d; i++) {
      if (X[i] > Y[j] + COVERAGE) {
        Y[++j] = X[i] + COVERAGE;
      }
    }
    return Arrays.copyOfRange(Y, 0, j + 1);
  }

  public static int[] minLocationsReversed(int... X) {
    return minLocationsReversed(X, X.length);
  }

  public static int[] minLocationsReversed(int[] X, int d) {
    if (d == 0) {
      return new int[0];
    } else if (d == 1) {
      return new int[]{X[0] - COVERAGE};
    }
    Arrays.sort(X);
    int[] Y = new int[d];
    Y[d - 1] = X[d - 1] - COVERAGE;
    int j = d - 1;
    for (int i = d - 2; i >= 0; i--) {
      if (X[i] < Y[j] - COVERAGE) {
        Y[--j] = X[i] - COVERAGE;
      }
    }
    return Arrays.copyOfRange(Y, j, d);
  }
}
