package com.example;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertEquals;

public class InternalsTest {
  @Test
  public void swap() {
    String[] A = new String[]{"Hendra", "Anggrian"};
    Internals.swap(A, 0, 1);
    assertEquals(A[0], "Anggrian");
    assertEquals(A[1], "Hendra");

    int[] B = {13, 19, 9, 5, 12, 8, 7, 4, 11, 2, 6, 21};
    Internals.swap(B, 1, 5);
    assertEquals(B[1], 8);
    assertEquals(B[5], 19);
  }

  @Test
  public void partition() {
    int[] A = {13, 19, 9, 5, 12, 8, 7, 4, 11, 2, 6, 21};
    Internals.partition(A, 0, A.length - 1);
    assertThat(A).asList().containsExactly(13, 19, 9, 5, 12, 8, 7, 4, 11, 2, 6, 21).inOrder();
  }
}
