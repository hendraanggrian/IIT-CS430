package com.example.sort;

public class InsertionSorts {
  private InsertionSorts() {
  }

  public static void insertionSort(int[] A) {
    insertionSort(A, 0, A.length - 1);
  }

  public static void insertionSort(int[] A, int p, int r) {
    if (p < r) {
      insertionSort(A, p, r - 1);
      int temp = A[r];
      int i = r - 1;
      while (i >= p && temp < A[i]) {
        A[i + 1] = A[i];
        i--;
      }
      A[i + 1] = temp;
    }
  }
}
