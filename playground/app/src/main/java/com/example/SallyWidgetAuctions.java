package com.example;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO: implement knapsackDynamic
public class SallyWidgetAuctions {
  private SallyWidgetAuctions() {
  }

  public static int maxProfitGreedy(int[] X, int[] Y, int n) {
    if (X.length != Y.length) {
      throw new IllegalStateException();
    }
    return maxProfitGreedy(X, Y, n, X.length);
  }

  public static int maxProfitGreedy(int[] X, int[] Y, int n, int m) {
    if (m <= 0 || n <= 0) {
      return 0;
    }
    Multimap<Double, Integer> multimap = TreeMultimap.create();
    for (int i = 0; i < m; i++) {
      multimap.put((double) Y[i] / X[i], i);
    }
    int remaining = n;
    int profit = 0;
    List<Integer> indices = new ArrayList<>(multimap.values());
    Collections.reverse(indices);
    for (int i : indices) {
      if (remaining - X[i] < 0) {
        continue;
      }
      remaining -= X[i];
      profit += Y[i];
    }
    return profit;
  }

  public static int maxProfitKnapsack(int[] X, int[] Y, int n) {
    if (X.length != Y.length) {
      throw new IllegalStateException();
    }
    return maxProfitKnapsack(X, Y, n, X.length);
  }

  public static int maxProfitKnapsack(int[] X, int[] Y, int n, int m) {
    if (m <= 0 || n <= 0) {
      return 0;
    }
    int previous = maxProfitKnapsack(X, Y, n, m - 1);
    if (X[m - 1] > n) {
      return previous;
    }
    int current = Y[m - 1] + maxProfitKnapsack(X, Y, n - X[m - 1], m - 1);
    return Math.max(previous, current);
  }
}
