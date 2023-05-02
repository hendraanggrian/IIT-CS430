package com.example.sort;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class QuicksortsTest {
  @Test
  public void quicksort() {
    int[] A = {4, 1, 3, 9, 7};
    Quicksorts.quicksort(A);
    assertThat(A).asList().containsExactly(1, 3, 4, 7, 9).inOrder();

    A = new int[]{2, 1, 6, 10, 4, 1, 3, 9, 7};
    Quicksorts.quicksort(A);
    assertThat(A).asList().containsExactly(1, 1, 2, 3, 4, 6, 7, 9, 10).inOrder();
  }

  @Test
  public void quicksortTailRecursive() {
    int[] A = {4, 1, 3, 9, 7};
    Quicksorts.quicksortTailRecursive(A);
    assertThat(A).asList().containsExactly(1, 3, 4, 7, 9).inOrder();

    A = new int[]{2, 1, 6, 10, 4, 1, 3, 9, 7};
    Quicksorts.quicksortTailRecursive(A);
    assertThat(A).asList().containsExactly(1, 1, 2, 3, 4, 6, 7, 9, 10).inOrder();
  }

  @Test
  public void quicksortTailRecursiveModified() {
    int[] A = {4, 1, 3, 9, 7};
    Quicksorts.quicksortTailRecursiveModified(A);
    assertThat(A).asList().containsExactly(1, 3, 4, 7, 9).inOrder();

    A = new int[]{2, 1, 6, 10, 4, 1, 3, 9, 7};
    Quicksorts.quicksortTailRecursiveModified(A);
    assertThat(A).asList().containsExactly(1, 1, 2, 3, 4, 6, 7, 9, 10).inOrder();
  }
}
