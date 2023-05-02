package com.example;

public class Permutations {
  private Permutations() {
  }

  public static void prettyPrint(int[] A) {
    prettyPrint(A, A.length, 0);
  }

  public static void prettyPrint(int[] A, int n, int k) {
    if (k == n - 1) {
      for (int i = 0; i < n; i++) {
        System.out.print(A[i] + " ");
      }
      System.out.println();
    } else {
      for (int i = k; i < n; i++) {
        Internals.swap(A, i, k);
        prettyPrint(A, n, k + 1);
        Internals.swap(A, i, k);
      }
    }
  }
}
