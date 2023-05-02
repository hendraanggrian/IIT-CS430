package com.example.tree;

import org.junit.Test;

import static com.example.tree.Tree.binarySearchTreeOf;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DnaTreesTest {
  @Test
  public void isInRange() {
    Tree T = binarySearchTreeOf(10, 20, 30, 40, 50, 60, 70, 80, 90);
    assertFalse(DnaTrees.isInRange(T, 0, 9));
    assertTrue(DnaTrees.isInRange(T, 10, 10));
    assertTrue(DnaTrees.isInRange(T, 11, 89));
    assertTrue(DnaTrees.isInRange(T, 90, 90));
    assertFalse(DnaTrees.isInRange(T, 91, 100));
  }
}
