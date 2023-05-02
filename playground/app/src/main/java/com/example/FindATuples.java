package com.example;

import java.util.Arrays;

public class FindATuples {
  private FindATuples() {
  }

  public static boolean hasTupleBruteForce(int[] A) {
    for (int i = 0; i < A.length - 2; i++) {
      for (int j = i + 1; j < A.length - 1; j++) {
        for (int k = j + 1; k < A.length; k++) {
          // because the input is unsorted, manually find the highest value and compare the rest
          int max = Math.max(A[i], Math.max(A[j], A[k]));
          if (A[i] + A[j] + A[k] - max == max) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public static boolean hasTupleSorted(int[] A) {
    Arrays.sort(A);
    for (int i = 0; i < A.length - 2; i++) {
      if (A[i] + A[i + 1] == A[i + 2]) {
        return true;
      }
    }
    return false;
  }
}
