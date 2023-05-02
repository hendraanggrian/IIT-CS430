package com.example.graph;

import org.junit.Test;

import static com.example.graph.Graph.Edge;
import static com.example.graph.Graph.undirectedGraphOf;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AthleticEventsTest {
  @Test
  public void badInput() {
    assertFalse(AthleticEvents.isConflict(undirectedGraphOf(new char[0])));
    assertFalse(AthleticEvents.isConflict(undirectedGraphOf(new char[]{'A'}), 'A'));
    assertTrue(AthleticEvents.isConflict(undirectedGraphOf(new char[]{'A'}), 'B'));
  }

  @Test
  public void test() {
    Graph graph = undirectedGraphOf(new char[]{'A', 'B', 'C', 'D', 'E', 'F'},
      new Edge('A', 'C'),
      new Edge('C', 'D'),
      new Edge('D', 'E'),
      new Edge('B', 'F'),
      new Edge('A', 'C'),
      new Edge('A', 'C'));
    assertTrue(AthleticEvents.isConflict(graph, 'A', 'B', 'E')); // Zach Williams
    assertFalse(AthleticEvents.isConflict(graph, 'C', 'D')); // Jennifer Hopkins
    assertTrue(AthleticEvents.isConflict(graph, 'C', 'E', 'F')); // Ivan Green
    assertTrue(AthleticEvents.isConflict(graph, 'D', 'F', 'A')); // Douglas May
    assertFalse(AthleticEvents.isConflict(graph, 'B', 'F')); // Katherine Nojwoi
  }
}
