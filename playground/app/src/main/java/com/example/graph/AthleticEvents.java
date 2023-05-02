package com.example.graph;

import com.example.graph.Graph.Edge;

public class AthleticEvents {
  private AthleticEvents() {
  }

  public static boolean isConflict(Graph G, char... events) {
    if (events.length == 0) {
      return false;
    } else if (events.length == 1) {
      for (char v : G.vertexes) {
        if (events[0] == v) {
          return false;
        }
      }
      return true;
    }
    int i = 0;
    int j = 1;
    outer:
    while (j < events.length) {
      for (Edge edge : G.edges) {
        if (edge.start == events[i] && edge.end == events[j]) {
          i++;
          j++;
          continue outer;
        }
      }
      return true;
    }
    return false;
  }
}
