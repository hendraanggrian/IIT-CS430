package com.example;

public class Internals {
  private Internals() {
  }

  public static <E> void swap(E[] A, int i, int j) {
    if (i == j) {
      return;
    }
    E temp = A[i];
    A[i] = A[j];
    A[j] = temp;
  }

  public static void swap(int[] A, int i, int j) {
    if (i == j) {
      return;
    }
    A[i] = A[i] + A[j];
    A[j] = A[i] - A[j];
    A[i] = A[i] - A[j];
  }

  public static int partition(int[] A, int p, int r) {
    int x = A[r];
    int i = p - 1;

    for (int j = p; j < r; j++) {
      if (A[j] <= x) {
        i++;
        swap(A, i, j);
      }
    }
    swap(A, i + 1, r);
    return i + 1;
  }
}
