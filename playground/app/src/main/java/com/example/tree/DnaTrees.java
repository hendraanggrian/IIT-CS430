package com.example.tree;

public class DnaTrees {
  private DnaTrees() {
  }

  public static boolean isInRange(Tree T, int a, int b) {
    if (a > b) {
      throw new IllegalArgumentException("Invalid input range");
    }
    if (T == null) {
      return false;
    }
    if (T.value < a) {
      return isInRange(T.right, a, b);
    } else if (T.value > b) {
      return isInRange(T.left, a, b);
    }
    return true;
  }
}
