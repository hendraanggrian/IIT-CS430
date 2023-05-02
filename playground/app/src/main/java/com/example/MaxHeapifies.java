package com.example;

// https://github.com/kevinsprong23/clrs/blob/master/src/com/kevinsprong/HeapSort.java
public class MaxHeapifies {
  private MaxHeapifies() {
  }

  public static void maxHeapify(int[] A, int i) {
    while (true) {
      int l = left(i);
      int r = right(i);
      int max;
      if (l <= A.length && A[l] > A[i]) {
        max = l;
      } else {
        max = i;
      }
      if (r <= A.length && A[r] > A[max]) {
        max = r;
      }
      if (max == i) {
        break;
      }
      Internals.swap(A, i, max);
      i = max;
    }
  }

  private static int left(int i) {
    return 2 * i + 1;
  }

  private static int right(int i) {
    return 2 * i + 2;
  }
}
