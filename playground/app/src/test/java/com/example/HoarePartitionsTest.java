package com.example;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class HoarePartitionsTest {
  @Test
  public void hoarePartition() {
    int[] A = {13, 19, 9, 5, 12, 8, 7, 4, 11, 2, 6, 21};
    HoarePartitions.hoarePartition(A, 0, A.length - 1);
    assertThat(A).asList().containsExactly(6, 2, 9, 5, 12, 8, 7, 4, 11, 19, 13, 21).inOrder();
  }
}
