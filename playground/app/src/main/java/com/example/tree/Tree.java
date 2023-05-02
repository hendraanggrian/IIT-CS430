package com.example.tree;

import java.util.Arrays;

public class Tree {
  public int value;
  public Tree left;
  public Tree right;

  private Tree(int value, Tree left, Tree right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }

  public static Tree binarySearchTreeOf(int... values) {
    Arrays.sort(values);
    Tree root = null;
    Tree current;
    for (int value : values) {
      if (root == null) {
        root = new Tree(value, null, null);
      } else {
        current = root;
        while (true) {
          if (value < current.value) {
            if (current.left == null) {
              current.left = new Tree(value, null, null);
              break;
            } else {
              current = current.left;
            }
          } else {
            if (current.right == null) {
              current.right = new Tree(value, null, null);
              break;
            } else {
              current = current.right;
            }
          }
        }
      }
    }
    return root;
  }
}
