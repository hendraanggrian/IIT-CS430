package com.example.sort;

import com.example.Internals;

public class Quicksorts {
  private Quicksorts() {
  }

  public static void quicksort(int[] A) {
    quicksort(A, 0, A.length - 1);
  }

  public static void quicksort(int[] A, int p, int r) {
    if (p < r) {
      int q = Internals.partition(A, p, r);
      quicksort(A, p, q - 1);
      quicksort(A, q + 1, r);
    }
  }

  public static void quicksortTailRecursive(int[] A) {
    quicksortTailRecursive(A, 0, A.length - 1);
  }

  public static void quicksortTailRecursive(int[] A, int p, int r) {
    while (p < r) {
      int q = Internals.partition(A, p, r);
      quicksortTailRecursive(A, p, q - 1);
      p = q + 1;
    }
  }

  public static void quicksortTailRecursiveModified(int[] A) {
    quicksortTailRecursiveModified(A, 0, A.length - 1);
  }

  public static void quicksortTailRecursiveModified(int[] A, int p, int r) {
    while (p < r) {
      int q = Internals.partition(A, p, r);
      if (q < p + (r - p) / 2) {
        quicksortTailRecursiveModified(A, p, q - 1);
        p = q + 1;
      } else {
        quicksortTailRecursiveModified(A, q + 1, r);
        r = q - 1;
      }
    }
  }
}
