package com.example;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FindATuplesTest {
  @Test
  public void hasTupleBruteForce() {
    assertTrue(FindATuples.hasTupleBruteForce(new int[]{2, 72, 21, 100, 19})); // 2 + 19 = 21
    assertFalse(FindATuples.hasTupleBruteForce(new int[]{2, 72, 21, 100, 18}));
  }

  @Test
  public void hasTupleSorted() {
    assertTrue(FindATuples.hasTupleSorted(new int[]{2, 72, 21, 100, 19})); // 2 + 19 = 21
    assertFalse(FindATuples.hasTupleSorted(new int[]{2, 72, 21, 100, 18}));
  }
}
