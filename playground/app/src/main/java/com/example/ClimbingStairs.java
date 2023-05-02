package com.example;

public class ClimbingStairs {
  private ClimbingStairs() {
  }

  public static int minCost(int[] cost) {
    return minCost(cost, cost.length);
  }

  public static int minCost(int[] cost, int n) {
    if (n == 0) {
      return 0;
    } else if (n == 1) {
      return cost[0];
    } else if (n == 2) {
      return cost[1];
    }
    int[] dp = new int[n];
    dp[0] = cost[0];
    dp[1] = cost[1];
    for (int i = 2; i < n; i++) {
      dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
    }
    return Math.min(dp[n - 1], dp[n - 2]);
  }
}
