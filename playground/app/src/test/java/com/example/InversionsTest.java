package com.example;

import org.junit.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class InversionsTest {
  @Test
  public void getInversions() {
    List<List<Integer>> inversions = Inversions.getInversions(new int[]{2, 3, 8, 6, 1});
    assertThat(inversions.get(0)).containsExactly(4, 5).inOrder();
    assertThat(inversions.get(1)).containsExactly(3, 5).inOrder();
    assertThat(inversions.get(2)).containsExactly(2, 5).inOrder();
    assertThat(inversions.get(3)).containsExactly(1, 5).inOrder();
    assertThat(inversions.get(4)).containsExactly(3, 4).inOrder();
  }

  @Test
  public void countInversions() {
    // assertEquals(5, Inversions.countInversions(new int[]{2, 3, 8, 6, 1}));
  }
}
