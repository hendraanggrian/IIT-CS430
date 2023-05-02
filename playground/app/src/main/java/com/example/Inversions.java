package com.example;

import java.util.ArrayList;
import java.util.List;

public class Inversions {
  private Inversions() {
  }

  public static List<List<Integer>> getInversions(int[] A) {
    List<List<Integer>> inversions = new ArrayList<>();
    for (int j = A.length - 1; j >= 1; j--) {
      for (int i = j - 1; i >= 0; i--) {
        if (A[i] > A[j]) {
          List<Integer> pair = new ArrayList<>();
          // starting index is 1
          pair.add(i + 1);
          pair.add(j + 1);
          inversions.add(pair);
        }
      }
    }
    return inversions;
  }

  // TODO: still not stable
  public static int countInversions(int[] A) {
    return countInversions(A, 0, A.length - 1);
  }

  // TODO: still not stable
  public static int countInversions(int[] A, int p, int r) {
    if (p >= r) {
      return 0;
    }
    int mid = (p + r) / 2;
    int left = countInversions(A, p, mid);
    int right = countInversions(A, mid + 1, r);
    return left + right + mergeSortAndCountInversions(A, p, mid, r);
  }

  // TODO: still not stable
  private static int mergeSortAndCountInversions(int[] A, int p, int q, int r) {
    // split into sub-arrays
    int[] left = new int[q - p + 1];
    int[] right = new int[r - q];
    for (int i = 0; i < left.length; ++i) {
      left[i] = A[p + i];
    }
    for (int j = 0; j < right.length; ++j) {
      right[j] = A[q + 1 + j];
    }
    // merge sub-arrays
    int i = 0;
    int j = 0;
    int k = p;
    int inversions = 0;
    while (i < left.length && j < right.length) {
      if (left[i] <= right[j]) {
        A[k] = left[i];
        i++;
      } else {
        inversions += left.length - i + 1;
        A[k] = right[j];
        j++;
      }
      k++;
    }
    return inversions;
  }
}
