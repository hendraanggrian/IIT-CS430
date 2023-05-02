package com.example.graph;

import com.example.graph.Graph.Edge;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class ReachableGraphs {
  private ReachableGraphs() {
  }

  public static boolean isReachable(Graph G, char start, char end) {
    if (start == end) {
      return true;
    }
    Map<Character, Boolean> visits = new TreeMap<>();
    for (char v : G.vertexes) {
      visits.put(v, false);
    }
    Queue<Character> bfs = new LinkedList<>();
    visits.put(start, true);
    bfs.add(start);
    while (!bfs.isEmpty()) {
      start = bfs.remove();
      for (Edge edge : G.edges) {
        if (edge.start != start) {
          continue;
        }
        if (edge.end == end) {
          return true;
        }
        if (!visits.get(edge.end)) {
          visits.put(edge.end, true);
          bfs.add(edge.end);
        }
      }
    }
    return false;
  }
}
