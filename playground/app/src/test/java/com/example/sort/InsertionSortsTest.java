package com.example.sort;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class InsertionSortsTest {
  @Test
  public void insertionSort() {
    int[] A = {4, 1, 3, 9, 7};
    InsertionSorts.insertionSort(A);
    assertThat(A).asList().containsExactly(1, 3, 4, 7, 9).inOrder();

    A = new int[]{2, 1, 6, 10, 4, 1, 3, 9, 7};
    InsertionSorts.insertionSort(A);
    assertThat(A).asList().containsExactly(1, 1, 2, 3, 4, 6, 7, 9, 10).inOrder();
  }
}
