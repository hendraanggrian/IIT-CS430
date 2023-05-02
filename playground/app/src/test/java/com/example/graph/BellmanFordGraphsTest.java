package com.example.graph;

import org.junit.Test;

import static com.example.graph.Graph.Edge;
import static com.example.graph.Graph.directedGraphOf;
import static com.google.common.truth.Truth.assertThat;

public class BellmanFordGraphsTest {
  // from HW5
  @Test
  public void numeric() {
    assertThat(BellmanFordGraphs.getShortestPaths(
      directedGraphOf(new char[]{'0', '1', '2', '3', '4', '5', '6'},
        new Edge('0', '1', 6),
        new Edge('0', '2', 5),
        new Edge('0', '3', 5),
        new Edge('2', '3', -2),
        new Edge('2', '1', -2),
        new Edge('1', '4', -1),
        new Edge('2', '4', 1),
        new Edge('3', '5', -1),
        new Edge('4', '6', 3),
        new Edge('5', '6', 3)), '0')
    ).containsExactly(0, 3, 5, 3, 2, 2, 5);
  }

  // as seen in https://www.programiz.com/dsa/bellman-ford-algorithm
  @Test
  public void alphabetic() {
    assertThat(BellmanFordGraphs.getShortestPaths(
      directedGraphOf(new char[]{'A', 'B', 'C', 'D', 'E'},
        new Edge('A', 'B', 4),
        new Edge('A', 'C', 2),
        new Edge('B', 'C', 3),
        new Edge('C', 'B', 1),
        new Edge('B', 'E', 3),
        new Edge('C', 'E', 5),
        new Edge('C', 'D', 4),
        new Edge('E', 'D', -5)), 'A')
    ).containsExactly(0, 3, 2, 1, 6);
  }
}
