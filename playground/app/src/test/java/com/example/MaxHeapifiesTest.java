package com.example;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class MaxHeapifiesTest {
  @Test
  public void maxHeapify() {
    int[] A = {10, 20, 25, 6, 12, 15, 4, 16};
    MaxHeapifies.maxHeapify(A, 0);
    assertThat(A).asList().containsExactly(25, 20, 15, 16, 12, 10, 4, 6);
  }
}
