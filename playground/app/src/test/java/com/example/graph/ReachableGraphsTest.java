package com.example.graph;

import org.junit.Test;

import static com.example.graph.Graph.Edge;
import static com.example.graph.Graph.undirectedGraphOf;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ReachableGraphsTest {
  @Test
  public void forward() {
    assertFalse(ReachableGraphs.isReachable(undirectedGraphOf(new char[]{'A', 'B', 'C'}),
      'A', 'C'));
    assertTrue(ReachableGraphs.isReachable(undirectedGraphOf(new char[]{'A', 'B', 'C', 'D'},
        new Edge('A', 'B'),
        new Edge('B', 'C'),
        new Edge('C', 'D')),
      'A', 'D'));
    assertTrue(ReachableGraphs.isReachable(undirectedGraphOf(new char[]{'0', '1', '2', '3'},
        new Edge('0', '1'),
        new Edge('0', '2'),
        new Edge('1', '2'),
        new Edge('2', '0'),
        new Edge('2', '3'),
        new Edge('3', '3')),
      '1', '3'));
  }

  @Test
  public void backward() {
    assertFalse(ReachableGraphs.isReachable(undirectedGraphOf(new char[]{'A', 'B', 'C'}),
      'A', 'C'));
    assertTrue(ReachableGraphs.isReachable(undirectedGraphOf(new char[]{'A', 'B', 'C', 'D'},
        new Edge('B', 'A'),
        new Edge('C', 'B'),
        new Edge('D', 'C')),
      'A', 'D'));
    assertTrue(ReachableGraphs.isReachable(undirectedGraphOf(new char[]{'0', '1', '2', '3'},
        new Edge('0', '1'),
        new Edge('0', '2'),
        new Edge('1', '2'),
        new Edge('2', '0'),
        new Edge('2', '3'),
        new Edge('3', '3')),
      '1', '3'));
  }
}
